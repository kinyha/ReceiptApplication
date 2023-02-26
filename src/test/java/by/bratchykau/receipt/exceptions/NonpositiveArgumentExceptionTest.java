package by.bratchykau.receipt.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonpositiveArgumentExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String message = "Value should be positive: ";
        NonpositiveArgumentException ex = new NonpositiveArgumentException(message);
        assertEquals(message, ex.getMessage());
    }

    @Test
    void testConstructorWithMessageAndValue() {
        String message = "Value should be positive: ";
        int value = -5;
        NonpositiveArgumentException ex = new NonpositiveArgumentException(message, value);
        assertEquals(message + value, ex.getMessage());
        assertEquals(value, ex.getValue());
    }

    @Test
    void testSetValue() {
        NonpositiveArgumentException ex = new NonpositiveArgumentException();
        int value = -5;
        ex.setValue(value);
        assertEquals(value, ex.getValue());
    }

    @Test
    void testGetMessage() {
        String message = "Value should be positive: ";
        int value = -5;
        NonpositiveArgumentException ex = new NonpositiveArgumentException(message, value);
        assertEquals(message + value, ex.getMessage());
    }

    @Test
    void testGetValue() {
        int value = -5;
        NonpositiveArgumentException ex = new NonpositiveArgumentException();
        ex.setValue(value);
        assertEquals(value, ex.getValue());
    }

}