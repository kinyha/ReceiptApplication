package by.bratchykau.receipt.exceptions;

public class LineException extends Exception {
    private int value;
    private String message;

    public LineException() {
    }

    public LineException(String str1) {
        super(str1);
    }

    public LineException(String message, int value) {
        super(message + value);
        this.value = value;
    }

}
