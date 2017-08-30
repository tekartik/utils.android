package com.tekartik.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.tekartik.utils.date.CalendarUtils.clearTime;
import static com.tekartik.utils.date.CalendarUtils.getNowToUtcCalendarSameTime;

/**
 * Created by alex on 31/03/17.
 */
public class Day extends DateBase {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    static {
        sdf.setTimeZone(Utc.timeZone);
    }

    public Day() {
        this(getNowToUtcCalendarSameTime(), false);
    }

    public Day(Day day) {
        this(day.getCalendar());
    }

    protected Day(Calendar calendar, boolean clone) {
        super(calendar, clone);
        clearTime(getCalendar());

    }
    public Day(Calendar calendar) {
        this(calendar, true);
    }

    @Override
    public SimpleDateFormat getSimpleDateFormat() {
        return sdf;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Day)) {
            return false;
        }
        return super.equals(obj);
    }

    static public Day parseDay(String dateText) {
        Calendar calendar = DateParser.parse(sdf, dateText);
        if (calendar != null) {
            return new Day(calendar);
        }

        return null;
    }

    public void add(int dayCount) {
        getCalendar().add(Calendar.DATE, dayCount);
    }

    public Day nextDay() {
        Day day = new Day(this);
        day.add(1);
        return day;
    }

}
