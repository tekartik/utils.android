package com.tekartik.utils.core;

/**
 * Created by alex on 31/03/17.
 */
public class IntegerUtils {

    public static boolean areEquals(Integer obj1, Integer obj2) {
        if (obj1 == null) {
            return obj2 == null;
        } else {
            return obj1.equals(obj2);
        }
    }

    public static int nonNull(Integer value, int defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static Integer parseInt(Object value) {
        if (value instanceof Integer) {
            return (Integer)value;
        } else if (value instanceof Long) {
            return (int)(long)(Long)value;
        } else if (value instanceof Float) {
            return Math.round((Float)value);
        } else if (value instanceof Double) {
            return (int)Math.round((Double)value);
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String)value);
            } catch (Exception ignore) {}
        } else if (value instanceof CharSequence) {
            try {
                return Integer.parseInt(value.toString());
            } catch (Exception ignore) {}
        }
        return null;
    }

    public static int parseInt(Object value, int defaultValue) {
        return nonNull(parseInt(value), defaultValue);
    }
}
