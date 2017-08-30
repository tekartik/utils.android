package com.tekartik.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.tekartik.utils.date.CalendarUtils.clearMonth;

/**
 * Created by alex on 31/03/17.
 */
public class Year extends DateBase {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    static {
        sdf.setTimeZone(Utc.timeZone);
    }

    public Year(Calendar calendar) {
        super(calendar, true);
        clearMonth(getCalendar());
    }

    @Override
    public SimpleDateFormat getSimpleDateFormat() {
        return sdf;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Year)) {
            return false;
        }
        return super.equals(obj);
    }

    static public Year parseYear(String dateText) {
        Calendar calendar = DateParser.parse(sdf, dateText);
        if (calendar != null) {
            return new Year(calendar);
        }

        return null;
    }

}
