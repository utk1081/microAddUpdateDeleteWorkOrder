package de.gloresoft.workorderapi.entities;

import java.time.ZonedDateTime;

public class ErrorMessageEntity {

    private final String message;
    private final String detail;
    private final ZonedDateTime timestamp;

    /**
     * Error Code denotes a special code that will tell the Rest Api Consumer what kind of error comes from the response.
     */
    private final int errorCode;

    public ErrorMessageEntity(String message, String detail, ZonedDateTime timestamp, int errorCode) {
        this.message = message;
        this.detail = detail;
        this.timestamp = timestamp;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
