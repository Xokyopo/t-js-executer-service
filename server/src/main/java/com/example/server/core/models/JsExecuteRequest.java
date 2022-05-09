package com.example.server.core.models;

public class JsExecuteRequest {

    private final String firstFunction;

    private final String secondFunction;

    private final int repeatsCount;

    private final AlignmentType responseAlignment;

    public JsExecuteRequest(String firstFunction, String secondFunction, int repeatsCount, AlignmentType responseAlignment) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
        this.repeatsCount = repeatsCount;
        this.responseAlignment = responseAlignment;
    }


    public String getFirstFunction() {
        return firstFunction;
    }

    public String getSecondFunction() {
        return secondFunction;
    }

    public int getRepeatsCount() {
        return repeatsCount;
    }

    public AlignmentType getResponseAlignment() {
        return responseAlignment;
    }

    public enum AlignmentType {
        CSV,
        BASIC
    }
}
