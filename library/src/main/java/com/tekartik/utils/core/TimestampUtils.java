package com.tekartik.utils.core;

/**
 * Created by alex on 28/09/17.
 */

public class TimestampUtils {

    static public boolean elapsed(Long timestamp1, Long timestamp2, long minDiff) {
        if (timestamp1 == null || timestamp2 == null) {
            return true;
        }
        if (Math.abs(timestamp1 - timestamp2) >= minDiff) {
            return true;
        }
        return false;
    }
}
