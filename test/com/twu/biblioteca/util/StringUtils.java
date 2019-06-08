package com.twu.biblioteca.util;

public class StringUtils {
    public static String cleanStringFromMarkers(String string) {
        return string.replaceAll("\\n", "");
    }
}
