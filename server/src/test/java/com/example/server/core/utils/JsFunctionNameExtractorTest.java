package com.example.server.core.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsFunctionNameExtractorTest {

    @Test
    public void extract_ShouldReturnTrue_WhenOnlyFunction() {
        String function = "function name(){}";

        String expected = "name";
        String actual = JsFunctionNameExtractor.extract(function);

        assertEquals(expected, actual);
    }

    @Test
    public void extract_ShouldReturnTrue_WhenFunctionHaveAlias() {
        String function = "a = function name(){}";

        String expected = "a";
        String actual = JsFunctionNameExtractor.extract(function);

        assertEquals(expected, actual);
    }

    @Test
    public void extract_ShouldReturnTrue_WhenFunctionHaveTwoAlias() {
        String function = "b = a = function name(){}";

        String expected = "b";
        String actual = JsFunctionNameExtractor.extract(function);

        assertEquals(expected, actual);
    }

    @Test
    public void extract_ShouldReturnTrue_WhenHaveConstantBeforeFunctionWithAlias() {
        String function = "constant = 5;a = function name(){}";

        String expected = "a";
        String actual = JsFunctionNameExtractor.extract(function);

        assertEquals(expected, actual);
    }

    @Test
    public void extract_ShouldReturnTrue_WhenHaveConstantWithoutSemicolonBeforeFunctionWithAlias() {
        String function = "constant = 5 a = function name(){}";

        String expected = "a";
        String actual = JsFunctionNameExtractor.extract(function);

        assertEquals(expected, actual);
    }
}
