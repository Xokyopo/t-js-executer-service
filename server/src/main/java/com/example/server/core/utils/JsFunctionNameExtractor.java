package com.example.server.core.utils;

public class JsFunctionNameExtractor {

    private static final String FUNCTION = "function";
    private static final String ANY_NOT_WORD_SING_REGEX = "\\W";
    private static final String FUNCTION_PARAMETERS_OPEN_SING_REGEX = "\\(";
    private static final int FIRST_ARRAYS_VALUE_INDEX = 0;

    public static String extract(String function) {
        String[] splittingByFunction = function.split(FUNCTION, 2);

        String alias = extractAlias(splittingByFunction[FIRST_ARRAYS_VALUE_INDEX]);

        if (alias == null) {
            return extractName(splittingByFunction[FIRST_ARRAYS_VALUE_INDEX + 1]);
        }

        return alias;
    }

    private static String extractName(String partWithName) {
        return partWithName.split(FUNCTION_PARAMETERS_OPEN_SING_REGEX, 2)[FIRST_ARRAYS_VALUE_INDEX].trim();
    }

    private static String extractAlias(String part) {
        String alias = null;
        String partWithAlias = part.trim();

        while (partWithAlias.endsWith("=")) {
            String cuttingString = substringBy(partWithAlias, "=").trim();
            String[] words = cuttingString.split(ANY_NOT_WORD_SING_REGEX);

            alias = words[words.length - 1].trim();

            partWithAlias = substringBy(cuttingString, alias).trim();
        }

        return alias;
    }

    private static String substringBy(String original, String extractValue) {
        return original.substring(0, original.length() - extractValue.length());
    }
}
