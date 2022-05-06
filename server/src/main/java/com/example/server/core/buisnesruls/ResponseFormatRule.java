package com.example.server.core.buisnesruls;

import com.example.server.core.models.FunctionResult;
import org.springframework.stereotype.Component;

@Component
public class ResponseFormatRule {

    private static final String BASIC_FORMAT_TEMPLATE = "№ %s, %s, %s, %s";
    private static final String CSV_FORMAT_TEMPLATE = "\"№ %s\", %s, %s, %s, %s, %s, %s";

    //<№ итерации>, <номер функции>, <результат функции>, <время расчета функции>
    public String basicResultFormat(FunctionResult result) {
        return String.format(BASIC_FORMAT_TEMPLATE,
                result.getIterationCount(),
                result.getFunctionNumber(),
                result.getResult(),
                result.getExecutedTime());
    }

    //<№ итерации>, <результат функции 1>, <время расчета функции 1>, <кол-во полученных наперед результатов функции 1>,
    //              <результат функции 2>, <время расчета функции 2>, <кол-во полученных наперед результатов функции 2>
    public String csvResultFormat(
            FunctionResult firstFunctionResult,
            int firsFunctionIterationCounter,
            FunctionResult secondFunctionResult,
            int secondFunctionIterationCounter
    ) {
        int firstReceivedAdvanceValues = firsFunctionIterationCounter - firstFunctionResult.getIterationCount();
        int secondReceivedAdvanceValues = secondFunctionIterationCounter - secondFunctionResult.getIterationCount();

        Object firstResult = firstFunctionResult.getResult();
        Object secondResult = secondFunctionResult.getResult();

        String firstResultFromCSV = (firstResult == null) ? null : FormattingValueToCSVRule.format(firstResult.toString());
        String secondResultFromCSV = (secondResult == null) ? null : FormattingValueToCSVRule.format(secondResult.toString());

        return String.format(CSV_FORMAT_TEMPLATE,
                firstFunctionResult.getIterationCount(),
                firstResultFromCSV,
                firstFunctionResult.getExecutedTime(),
                Math.max(firstReceivedAdvanceValues, 0),
                secondResultFromCSV,
                secondFunctionResult.getExecutedTime(),
                Math.max(secondReceivedAdvanceValues, 0));
    }
}
