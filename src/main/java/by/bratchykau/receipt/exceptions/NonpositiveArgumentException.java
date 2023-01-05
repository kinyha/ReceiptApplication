package by.bratchykau.receipt.exceptions;

public class NonpositiveArgumentException extends IllegalArgumentException {
    private String message;
    private int value;


    public NonpositiveArgumentException() {
    }

    public NonpositiveArgumentException(String message) {
        super(message);
    }

    public NonpositiveArgumentException(String message, int value) {
        super(message + value);
        this.value = value;
    }

}
