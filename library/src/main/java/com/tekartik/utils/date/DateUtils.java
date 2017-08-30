package com.tekartik.utils.date;

/**
 * Created by alex on 28/04/17.
 */

public class DateUtils {
    public static final long ONE_SECOND_IN_MILLIS = 1000L;
    public static final long ONE_MINUTE_IN_MILLIS = 60 * ONE_SECOND_IN_MILLIS;
    public static final long ONE_HOUR_IN_MILLIS = 60 * ONE_MINUTE_IN_MILLIS;
    public static final long ONE_DAY_IN_MILLIS = 24 * ONE_HOUR_IN_MILLIS;
    public static final long ONE_WEEK_IN_MILLIS = 7 * ONE_DAY_IN_MILLIS;

    public static java.util.Date withOffset(java.util.Date date, long millis) {
        return new java.util.Date(date.getTime() + millis);
    }

    public static int compare(java.util.Date date1, java.util.Date date2) {
        if (date1 == null) {
            if (date2 == null) {
                return 0;
            }
            return 1;
        } else if (date2 == null) {
            return -1;
        }
        if (date1.after(date2)) {
            return 1;
        } else if (date1.equals(date2)) {
            return 0;
        } else {
            return -1;
        }
    }


}
