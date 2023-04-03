package de.gloresoft.workorderapi.exceptions;

public class InsufficientBillaleDaysException extends RuntimeException {

    public InsufficientBillaleDaysException(String message) {
        super(message);
    }

    public InsufficientBillaleDaysException(String message, Throwable cause) {
        super(message, cause);
    }
}
