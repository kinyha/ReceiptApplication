package by.bratchykau.receipt.model;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ReceiptTest {
    @Test
    public void testToString() {
        Product product1 = new Product(1, "Product 1", 100);
        product1.setQuantity(5);
        Product product2 = new Product(2, "Product 2", 200);
        product2.setQuantity(6);
        DiscountCard discountCard = new DiscountCard("card1", 10);
        List<Product> products = Arrays.asList(product1, product2);
        Receipt receipt = new Receipt(products, 500, discountCard);
        String expected = "Receipt:\n"
                + "Product 1 x5 $1.00 = $5.00\n"
                + "Product 2 x6 $2.00 = $10.80 with discount\n"
                + "Total: $15.80\n"
                + "Discount card: card1 percent 10%\n";
        assertEquals(expected, receipt.toString());
    }
}