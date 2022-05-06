package com.example.server.core.models;

import com.example.server.core.buisnesruls.JsExecuteRule;

import java.util.Date;

public class FunctionResult {

    private final int iterationCount;
    private final int functionNumber;
    private final Object result;
    private final long executedTime;

    public FunctionResult(int functionNumber, int iterationCount, JsExecuteRule.Function function) {
        this.iterationCount = iterationCount;
        this.functionNumber = functionNumber;
        long beforeStartFunction = new Date().getTime();
        result = function.execute(iterationCount);
        long afterExecuteFunction = new Date().getTime();
        this.executedTime = afterExecuteFunction - beforeStartFunction;
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
