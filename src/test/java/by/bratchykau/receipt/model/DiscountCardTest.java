package by.bratchykau.receipt.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCardTest {
    @Test
    public void testConstructor() {
        // Test valid input
        DiscountCard discountCard = new DiscountCard("card1", 10);
        assertEquals("card1", discountCard.getNumber());
        assertEquals(10, discountCard.getDiscountPercentage());
    }
}
