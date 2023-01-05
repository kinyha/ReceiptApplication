package by.bratchykau.receipt.utils;

import by.bratchykau.receipt.model.DiscountCard;
import by.bratchykau.receipt.model.Product;
import by.bratchykau.receipt.model.Receipt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReceiptBuilderTest {
    @Test
    public void testBuild() {
        ReceiptBuilder builder = new ReceiptBuilder();
        builder.addProduct(new Product(1, "Product 1", 100, 5));
        builder.addProduct(new Product(2, "Product 2", 200, 5));
        builder.addProduct(new Product(3, "Product 3", 300, 5));

        DiscountCard discountCard = new DiscountCard("card-123", 10);
        builder.setDiscountCard(discountCard);

        Receipt receipt = builder.build();

        assertNotNull(receipt);
        assertEquals(3, receipt.getProducts().size());
        assertEquals(2700, receipt.getTotalAmount());
        assertEquals(discountCard, receipt.getDiscountCard());
    }
}