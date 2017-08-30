package com.tekartik.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * Created by alex on 31/03/17.
 */
public class CalendarUtils {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);

    public static Calendar withOffset(Calendar calendar, int millis) {
        // clone it
        calendar = (Calendar) calendar.clone();
        calendar.add(Calendar.MILLISECOND, millis);
        return calendar;
    }

    public static Calendar getCalendar(TimeZone timeZone, int year, int month, int day, int hour, int minute, int second) {
        return getCalendar(timeZone, year, month, day, hour, minute, second, 0);
    }

    public static Calendar getCalendar(TimeZone timeZone, int year, int month, int day, int hour, int minute) {
        return getCalendar(timeZone, year, month, day, hour, minute, 0, 0);
    }

    public static Calendar getCalendar(TimeZone timeZone, int year, int month, int day) {
        return getCalendar(timeZone, year, month, day, 0, 0, 0, 0);
    }

    public static Calendar getCalendar(TimeZone timeZone, int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar calendar = getCalendar(timeZone);
        calendar.set(YEAR, year);
        calendar.set(MONTH, month);
        calendar.set(DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar;
    }

    public static Calendar clearTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar clearDay(Calendar calendar) {
        calendar.set(DATE, 1);
        return clearTime(calendar);
    }

    public static Calendar clearMonth(Calendar calendar) {
        calendar.set(MONTH, 0);
        return clearDay(calendar);
    }

    public static int getDiffYears(Calendar calendar1, Calendar calendar2) {
        int diff = calendar2.get(YEAR) - calendar1.get(YEAR);
        if (calendar1.get(MONTH) > calendar2.get(MONTH) ||
                (calendar1.get(MONTH) == calendar2.get(MONTH) && calendar1.get(DATE) > calendar2.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static String utcFormat(Calendar calendar) {
        synchronized (Utc.sdf) {
            return Utc.sdf.format(calendar.getTime());
        }
    }

    public static String format(Calendar calendar) {
        synchronized (sdf) {
            return sdf.format(calendar.getTime());
        }
    }

    public static Calendar clone(Calendar calendar) {
        return (Calendar) calendar.clone();
    }

    public static Calendar getCalendar(TimeZone timeZone, long timeInMillis) {
        Calendar calendar = getCalendar(timeZone);
        calendar.setTimeInMillis(timeInMillis);
        return calendar;
    }

    public static Calendar getCalendar(TimeZone timeZone, Date date) {
        return getCalendar(timeZone, date.getTime());
    }

    public static Calendar getCalendar(TimeZone timeZone) {
        if (timeZone == null) {
            return Calendar.getInstance();
        } else {
            return Calendar.getInstance(timeZone);
        }
    }

    public static Calendar getNowToUtcCalendarSameTime() {
        return toUtcCalendarSameTime(Calendar.getInstance());
    }

    public static Calendar toUtcCalendarSameTime(Calendar calendar) {
        Calendar utcCalendar = Calendar.getInstance(Utc.timeZone);
        calendarCopyDateTime(calendar, utcCalendar);
        return utcCalendar;
    }


    public static void calendarCopyDateTime(Calendar src, Calendar dst) {
        calendarCopyDate(src, dst);
        calendarCopyTime(src, dst);
    }

    public static void calendarCopyDate(Calendar src, Calendar dst) {
        dst.set(Calendar.YEAR, src.get(Calendar.YEAR));
        dst.set(Calendar.MONTH, src.get(Calendar.MONTH));
        dst.set(Calendar.DATE, src.get(Calendar.DATE));
    }

    public static void calendarCopyTime(Calendar src, Calendar dst) {
        dst.set(Calendar.HOUR_OF_DAY, src.get(Calendar.HOUR_OF_DAY));
        dst.set(Calendar.MINUTE, src.get(Calendar.MINUTE));
        dst.set(Calendar.SECOND, src.get(Calendar.SECOND));
        dst.set(Calendar.MILLISECOND, src.get(Calendar.MILLISECOND));
    }

}
