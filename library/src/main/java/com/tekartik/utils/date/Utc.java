package com.tekartik.utils.date;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by alex on 31/03/17.
 */
public class Utc {
    public static TimeZone timeZone = TimeZone.getTimeZone("UTC");
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);

    static {
        sdf.setTimeZone(timeZone);
    }
}
