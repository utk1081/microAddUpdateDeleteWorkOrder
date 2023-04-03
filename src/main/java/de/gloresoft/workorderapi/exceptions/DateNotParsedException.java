package de.gloresoft.workorderapi.exceptions;

public class DateNotParsedException extends RuntimeException {

    public DateNotParsedException() {
    }

    public DateNotParsedException(String message) {
        super(message);
    }

    public DateNotParsedException(String message, Throwable cause) {
        super(message, cause);
    }
}
