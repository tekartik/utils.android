package com.tekartik.utils.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by alex on 31/03/17.
 */
public abstract class DateBase implements Date {
    Calendar calendar;

    abstract protected SimpleDateFormat getSimpleDateFormat();

    protected DateBase(Calendar calendar, boolean clone) {
        if (clone) {
            this.calendar = CalendarUtils.toUtcCalendarSameTime(calendar);
        } else {
            this.calendar = calendar;
        }
        if (!getCalendar().getTimeZone().equals(Utc.timeZone)) {
            throw new AssertionError("Calendar time zone must be utc");
        }
    }

    @Override
    public int hashCode() {
        return getCalendar().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Date)) {
            return false;
        }
        Date o = (Date) obj;
        return calendar.compareTo(o.getCalendar()) == 0;
    }

    @Override
    public String toString() {
        DateFormat sdf = getSimpleDateFormat();
        synchronized (sdf) {
            return sdf.format(getCalendar().getTime());
        }
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public boolean before(Date date) {
        return getCalendar().before(date.getCalendar());
    }

}
