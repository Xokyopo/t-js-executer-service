package com.example.server.core.exceptions;


public class ExceptionsFactory {

    public static FunctionCreatingException createFunctionCreatingException(Exception e) {
        return new FunctionCreatingException(e.getMessage(), e);
    }

    public static FunctionExecuteException createFunctionExecuteException(Exception e) {
        return new FunctionExecuteException(e.getMessage(), e);
    }
}
