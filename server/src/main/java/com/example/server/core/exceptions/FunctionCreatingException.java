package com.example.server.core.exceptions;

public class FunctionCreatingException extends RuntimeException {

    public FunctionCreatingException() {
    }

    public FunctionCreatingException(String message) {
        super(message);
    }

    public FunctionCreatingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FunctionCreatingException(Throwable cause) {
        super(cause);
    }

    public FunctionCreatingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
