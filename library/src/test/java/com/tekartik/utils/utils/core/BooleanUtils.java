package com.tekartik.utils.utils.core;

public class BooleanUtils {

    public static boolean isTrue(Boolean value) {
        return Boolean.TRUE.equals(value);
    }

    public static Boolean trueOrNull(Boolean value) {
        if (isTrue(value)) {
            return value;
        }
        return null;
    }
}
