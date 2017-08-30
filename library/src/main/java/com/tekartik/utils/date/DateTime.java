package com.tekartik.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by alex on 31/03/17.
 */
public class DateTime {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);

    Calendar calendar;

    public DateTime() {
        this(CalendarUtils.getCalendar(null));
    }

    public DateTime(Calendar calendar) {
        this.calendar = (Calendar) calendar.clone();
    }

    public DateTime(TimeZone timeZone, int year, int month, int day) {
        this(CalendarUtils.getCalendar(timeZone, year, month, day, 0, 0, 0, 0));
    }

    public DateTime(TimeZone timeZone, int year, int month, int day, int hour, int minute, int second, int millisecond) {
        this(CalendarUtils.getCalendar(timeZone, year, month, day, hour, minute, second, millisecond));
    }

    @Override
    public String toString() {
        synchronized (sdf) {
            sdf.setTimeZone(calendar.getTimeZone());
            return sdf.format(calendar.getTime());
        }
    }

    public Calendar getCalendar() {
        return calendar;
    }

    @Override
    public int hashCode() {
        return getCalendar().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DateTime)) {
            return false;
        }
        DateTime o = (DateTime) obj;
        return calendar.equals(o.getCalendar());
    }

    static public DateTime parse(String dateText) {
        if (dateText == null) {
            return null;
        }
        synchronized (sdf) {
            try {
                java.util.Date date = sdf.parse(dateText);
                if (date != null) {
                    return new DateTime(sdf.getCalendar());
                }
                //return new Day(DateUtils.localDateToUtcCalendarSameTime(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int compare(DateTime date1, DateTime date2) {
        return DateUtils.compare(date1 == null ? null : date1.getCalendar().getTime(), date2 == null ? null : date2.getCalendar().getTime());
    }

    public boolean after(DateTime dateTime) {
        return getCalendar().after(dateTime.getCalendar());
    }

    public boolean before(DateTime dateTime) {
        return getCalendar().before(dateTime.getCalendar());
    }
}
