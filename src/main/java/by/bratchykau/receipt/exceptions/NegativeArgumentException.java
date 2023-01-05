package by.bratchykau.receipt.exceptions;

public class NegativeArgumentException extends NonpositiveArgumentException {
        private String message;
        private  int value;


        public NegativeArgumentException() {
        }

        public NegativeArgumentException(String message) {
            super(message);
        }

        public NegativeArgumentException(String message, int value) {
            super(message + value);
            this.value = value;
        }


}
