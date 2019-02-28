package com.tekartik.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.tekartik.utils.date.CalendarUtils.clearDay;

/**
 * Created by alex on 31/03/17.
 */
public class Month extends DateBase {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

    static {
        sdf.setTimeZone(Utc.timeZone);
    }

    public Month(Calendar calendar) {
        super(calendar, true);
        clearDay(getCalendar());
    }

    static public Month parseMonth(String dateText) {
        Calendar calendar = DateParser.parse(sdf, dateText);
        if (calendar != null) {
            return new Month(calendar);
        }

        return null;
    }

    @Override
    public SimpleDateFormat getSimpleDateFormat() {
        return sdf;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Month)) {
            return false;
        }
        return super.equals(obj);
    }

}
