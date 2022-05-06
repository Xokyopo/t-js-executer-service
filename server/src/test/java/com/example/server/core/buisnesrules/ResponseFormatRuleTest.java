package com.example.server.core.buisnesrules;

import com.example.server.core.buisnesruls.ResponseFormatRule;
import com.example.server.core.models.FunctionResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ResponseFormatRuleTest {

    private ResponseFormatRule responseFormatRule;

    @BeforeEach
    public void init() {
        this.responseFormatRule = new ResponseFormatRule();
    }

    //<№ итерации>, <номер функции>, <результат функции>, <время расчета функции>
    @Test
    public void basicResultFormat_ShouldTrue_WhenExecute() {
        FunctionResult functionResult = Mockito.mock(FunctionResult.class);

        Mockito.when(functionResult.getResult()).thenReturn("text");
        Mockito.when(functionResult.getExecutedTime()).thenReturn(100L);
        Mockito.when(functionResult.getFunctionNumber()).thenReturn(1);
        Mockito.when(functionResult.getIterationCount()).thenReturn(5);

        String expected = "№ 5, 1, text, 100";
        String actual = this.responseFormatRule.basicResultFormat(functionResult);

        assertEquals(expected, actual);
    }

    //<№ итерации>, <результат функции 1>, <время расчета функции 1>, <кол-во полученных наперед результатов функции 1 >,
    // <результат функции 2>, <время расчета функции 2>, <кол-во полученных наперед результатов функции 2 >
    @Test
    public void csvResultFormat_ShouldReturnTrue_WhenExecute() {
        int iterationCount = 5;
        int firstFunctionIterationCounter = 10;
        int secondFunctionIterationCounter = 5;

        FunctionResult firstFunctionResult = Mockito.mock(FunctionResult.class);

        Mockito.when(firstFunctionResult.getResult()).thenReturn("text");
        Mockito.when(firstFunctionResult.getExecutedTime()).thenReturn(100L);
        Mockito.when(firstFunctionResult.getFunctionNumber()).thenReturn(1);
        Mockito.when(firstFunctionResult.getIterationCount()).thenReturn(iterationCount);

        FunctionResult secondFunctionResult = Mockito.mock(FunctionResult.class);

        Mockito.when(secondFunctionResult.getResult()).thenReturn("text; with \"reserved\" CVS\nformat, characters");
        Mockito.when(secondFunctionResult.getExecutedTime()).thenReturn(500L);
        Mockito.when(secondFunctionResult.getFunctionNumber()).thenReturn(2);
        Mockito.when(secondFunctionResult.getIterationCount()).thenReturn(iterationCount);

        String expected = "\"№ 5\", text, 100, 5, \"text; with \"\"reserved\"\" CVS\nformat, characters\", 500, 0";
        String actual = this.responseFormatRule.csvResultFormat(
                firstFunctionResult,
                firstFunctionIterationCounter,
                secondFunctionResult,
                secondFunctionIterationCounter
        );

        assertEquals(expected, actual);
    }
}
