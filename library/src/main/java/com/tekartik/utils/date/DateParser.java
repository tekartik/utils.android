package com.tekartik.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by alex on 31/03/17.
 */
public class DateParser {

    static public Date parse(String dateStr) {

        Day day = Day.parseDay(dateStr);
        if (day != null) {
            return day;
        }
        Month month = Month.parseMonth(dateStr);
        if (month != null) {
            return month;
        }
        Year year = Year.parseYear(dateStr);
        if (year != null) {
            return year;
        }
        return null;
    }

    static public Calendar parse(DateFormat dateFormat, String dateText) {
        if (dateText == null) {
            return null;
        }
        synchronized (dateFormat) {
            try {
                java.util.Date date = dateFormat.parse(dateText);
                if (date != null) {
                    return dateFormat.getCalendar();
                }
                //return new Day(DateUtils.localDateToUtcCalendarSameTime(date));
            } catch (ParseException e) {
                // e.printStackTrace();
            }

            return null;
        }
    }
}
