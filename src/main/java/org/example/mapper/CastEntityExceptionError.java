package org.example.mapper;

public class CastEntityExceptionError extends RuntimeException {
    public CastEntityExceptionError() {
        super();
    }

    public CastEntityExceptionError(String message) {
        super(message);
    }

    public CastEntityExceptionError(String message, Throwable cause) {
        super(message, cause);
    }

    public CastEntityExceptionError(Throwable cause) {
        super(cause);
    }

    protected CastEntityExceptionError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
