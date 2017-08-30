package com.tekartik.utils.core;

public class ValueUtils {
    public static <T> boolean areEquals(T obj1, T obj2) {
        if (obj1 == null) {
            return obj2 == null;
        } else {
            return obj1.equals(obj2);
        }
    }

    public static <T> T nonNull(T value, T defaultValue) {
        return (value == null) ? defaultValue : value;
    }
}
