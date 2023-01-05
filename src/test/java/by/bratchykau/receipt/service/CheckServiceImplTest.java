package by.bratchykau.receipt.service;


import by.bratchykau.receipt.model.DiscountCard;
import by.bratchykau.receipt.model.Product;
import by.bratchykau.receipt.repositories.DiscountCardRepository;
import by.bratchykau.receipt.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CheckServiceImplTest {
    private ProductRepository productRepository;
    private DiscountCardRepository discountCardRepository;
    private CheckServiceImpl checkService;
    private Product product;

    @BeforeEach
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        discountCardRepository = mock(DiscountCardRepository.class);
        checkService = new CheckServiceImpl(productRepository, discountCardRepository);
        when(productRepository.findById(1)).thenReturn(new Product(1, "Product 1", 200,1));
        when(productRepository.findById(2)).thenReturn(new Product(2, "Product 2", 300,1));
        when(discountCardRepository.findByNumber("card-123")).thenReturn(new DiscountCard("card-123", 10));
    }

    @Test
    public void testGetCheck() {
        String[] itemIdsAndQuantities = {"1-1"};
        String discountCard = "card-123";
        String check = checkService.getCheck(itemIdsAndQuantities, discountCard);
        String expected = "Receipt:\n" +
                "Product 1 x1 $2.00 = $2.00\n" +
                "Total: $1.80\n" +
                "Discount card: card-123 percent 10%\n";
        assertEquals(expected, check);
    }

    @Test
    public void testGetCheckWithoutDiscountCard() {
        String[] itemIdsAndQuantities = {"1-2", "2-3"};
        String discountCard = null;
        String check = checkService.getCheck(itemIdsAndQuantities, discountCard);
        String expected = "Receipt:\n" +
                "Product 1 x2 $2.00 = $4.00\n" +
                "Product 2 x3 $3.00 = $9.00\n" +
                "Total: $13.00\n";
        assertEquals(expected, check);
    }

    @Test
    public void testGetCheckWithInvalidDiscountCard() {
        String[] itemIdsAndQuantities = {"1-2", "2-3"};
        String discountCard = "invalid-card";
        String check = checkService.getCheck(itemIdsAndQuantities, discountCard);
        String expected = "Receipt:\n" +
                "Product 1 x2 $2.00 = $4.00\n" +
                "Product 2 x3 $3.00 = $9.00\n" +
                "Total: $13.00\n";
        assertEquals(expected, check);
    }

    @Test
    public void testGetCheckWithDifferentDiscountPercentages() {
        String[] itemIdsAndQuantities = {"1-2", "2-3"};
        String discountCard = "card-123";
        String check = checkService.getCheck(itemIdsAndQuantities, discountCard);
        String expected = "Receipt:\n" +
                "Product 1 x2 $2.00 = $4.00\n" +
                "Product 2 x3 $3.00 = $9.00\n" +
                "Total: $11.70\n" +
                "Discount card: card-123 percent 10%\n";
        assertEquals(expected, check);

        // update the discount percentage on the discount card
        DiscountCard card = discountCardRepository.findByNumber(discountCard);
        card.setDiscountPercentage(20);
        check = checkService.getCheck(itemIdsAndQuantities, discountCard);
        String expected2 = "Receipt:\n" +
                "Product 1 x2 $2.00 = $4.00\n" +
                "Product 2 x3 $3.00 = $9.00\n" +
                "Total: $10.40\n" +
                "Discount card: card-123 percent 20%\n";
        assertEquals(expected2, check);
    }

    @Test
    public void testGetCheckWithEmptyList() {
        String[] itemIdsAndQuantities = {};
        String discountCard = "card-123";
        String check = checkService.getCheck(itemIdsAndQuantities, discountCard);
        String expected = "Receipt:\n" +
                "Total: $0.00\n" +
                "Discount card: card-123 percent 10%\n";
        assertEquals(expected, check);
    }
}