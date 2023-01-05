package by.bratchykau.receipt.model;


import by.bratchykau.receipt.exceptions.NonpositiveArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {
    @Test
    public void testConstructor() {
        // Test valid input
        Product product = new Product(1, "Product 1", 100);
        assertEquals(1, product.getId());
        assertEquals("Product 1", product.getName());
        assertEquals(100, product.getPrice());

        // Test non-positive price
        Exception exception = assertThrows(NonpositiveArgumentException.class, () -> new Product(1, "Product 1", 0));
        assertEquals("Wrong argument for value: 0", exception.getMessage());

        // Test empty name
        exception = assertThrows(IllegalArgumentException.class, () -> new Product(1, "", 100));
        assertEquals("Name is empty", exception.getMessage());
    }

    @Test
    public void testSetters() {
        Product product = new Product();
        product.setId(1);
        product.setName("Product 1");
        product.setPrice(100);
        product.setQuantity(10);

        assertEquals(1, product.getId());
        assertEquals("Product 1", product.getName());
        assertEquals(100, product.getPrice());
        assertEquals(10, product.getQuantity());
    }
}
