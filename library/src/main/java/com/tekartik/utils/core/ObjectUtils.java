package com.tekartik.utils.core;

/**
 * Created by alex on 31/03/17.
 */
public class ObjectUtils {

    public static boolean areEquals(Object obj1, Object obj2) {
        if (obj1 == null) {
            return obj2 == null;
        } else {
            return obj1.equals(obj2);
        }
    }

    public static <T> T nonNull(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }
}
