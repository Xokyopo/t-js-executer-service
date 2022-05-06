package com.example.server.core.buisnesrules;

import com.example.server.core.buisnesruls.JsExecuteRule;
import com.example.server.core.exceptions.ExceptionsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class JsExecuteRuleTest {

    private JsExecuteRule jsExecuteRule;

    @BeforeEach
    public void init() {
        this.jsExecuteRule = new JsExecuteRule();
    }

    @Test
    public void createFunction_ShouldReturnTrue_WhenExecuteEmptyFunction() {
        String functionScript = "function a() {}";

        try {
            JsExecuteRule.Function function = this.jsExecuteRule.createFunction(functionScript);
            assertNotNull(function);

            Object result = function.execute();
            assertNull(result);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void createFunction_ShouldReturnTrue_WhenExecuteFunctionWithReturnedValue() {
        String functionScript = "function a() {return 1}";

        try {

            JsExecuteRule.Function function = this.jsExecuteRule.createFunction(functionScript);
            assertNotNull(function);

            Object expected = 1;
            Object actual = function.execute();
            assertEquals(expected, actual);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void createFunction_ShouldThrowException_WhenCreateLambdaFunction() {
        String functionScript = "a() => {}";


        Class<? extends Exception> expected = ExceptionsFactory.createFunctionCreatingException(new Exception()).getClass();
        Executable actual = () -> this.jsExecuteRule.createFunction(functionScript);

        assertThrows(expected, actual);
    }

    @Test
    public void createFunction_ShouldThrowException_WhenFunctionHasError() {
        String functionScript = "function a() {";


        Class<? extends Exception> expected = ExceptionsFactory.createFunctionCreatingException(new Exception()).getClass();
        Executable actual = () -> this.jsExecuteRule.createFunction(functionScript);

        assertThrows(expected, actual);
    }
}
