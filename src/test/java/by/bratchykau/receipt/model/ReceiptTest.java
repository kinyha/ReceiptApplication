package by.bratchykau.receipt.model;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ReceiptTest {
    @Test
    public void checkToString() {
        Product product1 = new Product(1, "Product 1", 200);
        product1.setQuantity(5);
        Product product2 = new Product(2, "Product 2", 200);
        product2.setQuantity(6);
        DiscountCard discountCard = new DiscountCard("card1", 10);
        List<Product> products = Arrays.asList(product1, product2);
        Receipt receipt = new Receipt(products, discountCard);

        String expected = "Receipt:\n"
                + "Product 1 x5 $2.00 = $10.00\n"
                + "Product 2 x6 $2.00 = $10.80 with discount\n"
                + "Total: $18.72\n"
                + "Discount card: card1 percent 10%\n";
        assertEquals(expected, receipt.toString());
    }

    @Test
    void checkFindTotalAmountWithoutDiscountCard() {
        List<Product> products = List.of(
                new Product(1, "Product 1", 100, 2),
                new Product(2, "Product 2", 200, 3)
        );
        Receipt receipt = new Receipt(products,  null);
        int expectedTotal = 800;
        int actualTotal = receipt.findTotalAmount();
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    void checkFindTotalAmountWithDiscountCard() {
        List<Product> products = List.of(
                new Product(1, "Product 1", 100, 2),
                new Product(2, "Product 2", 200, 3)
        );
        DiscountCard discountCard = new DiscountCard("card-1", 10);
        Receipt receipt = new Receipt(products, discountCard);
        int expectedTotal = 720;
        int actualTotal = receipt.findTotalAmount();
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    void checkFindTotalAmountWithLargeQuantityDiscount() {
        List<Product> products = List.of(
                new Product(1, "Product 1", 100, 6),
                new Product(2, "Product 2", 200, 3)
        );
        Receipt receipt = new Receipt(products, null);
        int expectedTotal = 1140;
        int actualTotal = receipt.findTotalAmount();
        assertEquals(expectedTotal, actualTotal);
    }
}