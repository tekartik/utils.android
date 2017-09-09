package com.tekartik.utils.core;

/**
 * Created by alex on 31/03/17.
 */
public class LongUtils {

    public static boolean areEquals(Long obj1, Long obj2) {
        if (obj1 == null) {
            return obj2 == null;
        } else {
            return obj1.equals(obj2);
        }
    }

    public static long nonNull(Long value, long defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static Long parseLong(Object value) {
        if (value instanceof Long) {
            return (Long)value;
        } else if (value instanceof Integer) {
            return ((Integer)value).longValue();
        } else if (value instanceof Float) {
            return (long)Math.round((Float)value);
        } else if (value instanceof Double) {
            return (long)Math.round((Double)value);
        } else if (value instanceof String) {
            try {
                return Long.parseLong((String)value);
            } catch (Exception ignore) {}
        } else if (value instanceof CharSequence) {
            try {
                return Long.parseLong(value.toString());
            } catch (Exception ignore) {}
        }
        return null;
    }

    public static long parseLong(Object value, long defaultValue) {
        return nonNull(parseLong(value), defaultValue);
    }
}
