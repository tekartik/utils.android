package com.tekartik.utils.utils.date;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by alex on 31/03/17.
 */
public class UtcDateTime extends DateTime {

    public UtcDateTime() {
        this(CalendarUtils.getCalendar(Utc.timeZone));
    }

    public UtcDateTime(Calendar calendar) {
        super(calendar);
        calendar = getCalendar();
        if (!calendar.getTimeZone().equals(Utc.timeZone)) {
            throw new AssertionError("Calendar time zone must be utc");
        }
        this.calendar = calendar;
    }

    public UtcDateTime(int year, int month, int day) {
        this(year, month, day, 0, 0, 0, 0);
    }

    public UtcDateTime(int year, int month, int day, int hour, int minute) {
        this(year, month, day, hour, minute, 0, 0);
    }

    public UtcDateTime(int year, int month, int day, int hour, int minute, int second) {
        this(year, month, day, hour, minute, second, 0);
    }

    public UtcDateTime(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        this(CalendarUtils.getCalendar(Utc.timeZone, year, month, day, hour, minute, second, millisecond));
    }

    @Override
    public String toString() {
        synchronized (Utc.sdf) {
            return Utc.sdf.format(calendar.getTime());
        }
    }

    static public UtcDateTime parse(String dateText) {
        if (dateText == null) {
            return null;
        }
        synchronized (Utc.sdf) {
            try {
                java.util.Date date = Utc.sdf.parse(dateText);
                if (date != null) {
                    return new UtcDateTime(Utc.sdf.getCalendar());
                }
                //return new Day(DateUtils.localDateToUtcCalendarSameTime(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        return getCalendar().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UtcDateTime)) {
            return false;
        }
        UtcDateTime o = (UtcDateTime) obj;
        return calendar.compareTo(o.getCalendar()) == 0;
    }
}
