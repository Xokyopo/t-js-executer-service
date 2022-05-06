package com.example.server.core.buisnesruls;

import java.util.List;


public class FormattingValueToCSVRule {

    private static final List<String> RESERVED_CHARACTERS = List.of(",", ";", "\n", " ");
    private static final String QUOTE = "\"";


    public static String format(String text) {
        if (text == null) {
            return null;
        }

        if (text.contains(QUOTE)) {
            return QUOTE + text.replaceAll(QUOTE, QUOTE + QUOTE) + QUOTE;
        }

        if (RESERVED_CHARACTERS.stream().anyMatch(text::contains)) {
            return QUOTE + text + QUOTE;
        }

        return text;
    }

}
