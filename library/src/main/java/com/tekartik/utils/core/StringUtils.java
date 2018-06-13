package com.tekartik.utils.core;

public class StringUtils {

    /**
     * <pre>
     * "" => null
     * null => null
     * "some text" => "some text"
     * </pre>
     *
     * @param string
     * @return
     */
    static public String nonEmpty(String string) {
        return nonEmpty(string, null);
    }

    static public String nonEmpty(String string, String defaultValue) {
        if (isEmpty(string)) {
            return defaultValue;
        }
        return string;
    }

    static public boolean isEmpty(CharSequence text) {
        if ((text == null) || (text.length() == 0)) {
            return true;
        }
        return false;
    }

    static public boolean isWhitespaces(CharSequence text) {
        if (isEmpty(text)) {
            return true;
        }
        if (text.toString().trim().isEmpty()) {
            return true;
        }
        return false;
    }

    static public String nonNull(String string) {
        if (string == null) {
            return "";
        }
        return string;
    }

    static public String toString(Object object) {
        if (object == null) {
            return null;
        } else {
            return object.toString();
        }
    }

    static public String truncate(String string, int lenMax) {
        if ((string != null) && (string.length() > lenMax)) {
            return string.substring(0, lenMax);
        }
        return string;
    }

    static public String fill(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(i % 10);
        }
        return sb.toString();
    }

    static public String capitalizeFirstLetter(String text) {
        if (isEmpty(text)) {
            return text;
        }
        StringBuilder sb = new StringBuilder(text);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }
}
