package com.example.server.core.models;

public class FunctionResult {

    private final int iterationCount;
    private final int functionNumber;
    private final Object result;
    private final long executedTime;

    public FunctionResult(int iterationCount, int functionNumber, Object result, long executedTime) {
        this.iterationCount = iterationCount;
        this.functionNumber = functionNumber;
        this.result = result;
        this.executedTime = executedTime;
    }

    public int getIterationCount() {
        return iterationCount;
    }

    public int getFunctionNumber() {
        return functionNumber;
    }

    public Object getResult() {
        return result;
    }

    public long getExecutedTime() {
        return executedTime;
    }
}
