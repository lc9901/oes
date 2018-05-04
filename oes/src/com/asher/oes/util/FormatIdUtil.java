package com.asher.oes.util;

public class FormatIdUtil {
    public static String formatId(String prefix, String id, int length) {
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = length; i > id.length(); i--) {
            sb.append("0");
        }
        sb.append(id);
        return sb.toString();
    }

    public static String formatId(String prefix, int id, int length) {
        return formatId(prefix, id+"", length);
    }
}