package com.example.server.core.buisnesrules;

import com.example.server.core.buisnesruls.FormattingValueToCSVRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FormattingValueToCSVRuleTest {

    @Test
    public void format_ShouldReturnTrue_WhenExecute() {
        String text = "simple, text; for \"test\" this\n\r function";

        String expected = "\"simple, text; for \"\"test\"\" this\n\r function\"";
        String actual = FormattingValueToCSVRule.format(text);
        assertEquals(expected, actual);
    }

    @Test
    public void format_ShouldReturnTrue_WhenTextWithQuote() {
        String text = "\"quote\"";

        String expected = "\"\"\"quote\"\"\"";
        String actual = FormattingValueToCSVRule.format(text);
        assertEquals(expected, actual);
    }

    @Test
    public void format_ShouldReturnTrue_WhenTextWithNewLine() {
        String text = "quote\n\r";

        String expected = "\"quote\n\r\"";
        String actual = FormattingValueToCSVRule.format(text);
        assertEquals(expected, actual);
    }

    @Test
    public void format_ShouldReturnTrue_WhenTextWithSemicolon() {
        String text = "quote;";

        String expected = "\"quote;\"";
        String actual = FormattingValueToCSVRule.format(text);
        assertEquals(expected, actual);
    }

    @Test
    public void format_ShouldReturnTrue_WhenTextWithComma() {
        String text = "quote,";

        String expected = "\"quote,\"";
        String actual = FormattingValueToCSVRule.format(text);
        assertEquals(expected, actual);
    }

    @Test
    public void format_ShouldReturnNull_WhenParameterAreNull() {
        assertNull(FormattingValueToCSVRule.format(null));
    }
}
