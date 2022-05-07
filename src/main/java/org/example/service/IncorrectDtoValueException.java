package org.example.service;

public class IncorrectDtoValueException extends Exception {
    public IncorrectDtoValueException() {
    }

    public IncorrectDtoValueException(String message) {
        super(message);
    }

    public IncorrectDtoValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDtoValueException(Throwable cause) {
        super(cause);
    }

    public IncorrectDtoValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
