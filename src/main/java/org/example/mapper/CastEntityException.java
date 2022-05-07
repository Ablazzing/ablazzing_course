package org.example.mapper;

public class CastEntityException extends RuntimeException{
    public CastEntityException() {
    }

    public CastEntityException(String message) {
        super(message);
    }

    public CastEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public CastEntityException(Throwable cause) {
        super(cause);
    }

    public CastEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
