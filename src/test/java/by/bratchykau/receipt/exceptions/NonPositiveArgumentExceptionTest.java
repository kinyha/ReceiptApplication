package by.bratchykau.receipt.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonPositiveArgumentExceptionTest {

    @Test
    void checkConstructorWithMessage() {
        String message = "Value should be positive: ";
        NonpositiveArgumentException ex = new NonpositiveArgumentException(message);
        assertEquals(message, ex.getMessage());
    }

    @Test
    void checkConstructorWithMessageAndValue() {
        String message = "Value should be positive: ";
        int value = -5;
        NonpositiveArgumentException ex = new NonpositiveArgumentException(message, value);
        assertEquals(message + value, ex.getMessage());
        assertEquals(value, ex.getValue());
    }

    @Test
    void checkSetValue() {
        NonpositiveArgumentException ex = new NonpositiveArgumentException();
        int value = -5;
        ex.setValue(value);
        assertEquals(value, ex.getValue());
    }
}