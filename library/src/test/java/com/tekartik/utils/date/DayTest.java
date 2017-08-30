package com.tekartik.utils.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.TimeZone;

import static java.util.Calendar.YEAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class DayTest {

    @Test
    public void dayToString() {
        Day day = new Day(new UtcDateTime(2017, 1, 2, 3, 4).getCalendar());
        //devLog(utcFormat(day.getCalendar()));
        assertEquals("2017-02-02", day.toString());
    }

    @Test
    public void parseDate() {
        assertEquals(null, Day.parseDay("2017"));
        assertEquals(null, Day.parseDay("2017-01"));
        assertEquals("2017-01-01", Day.parseDay("2017-01-01").toString());

        Day day1 = Day.parseDay("2017-01-01");
        Day day2 = Day.parseDay("2017-01-01");
        assertNotNull(day1);
        assertEquals(day1, day2);

        assertEquals(new Day(new UtcDateTime(2017, 1, 2, 3, 4).getCalendar()),
                new Day(new UtcDateTime(2017, 1, 2, 3, 5).getCalendar()));
        assertNotEquals(new Day(new UtcDateTime(2017, 1, 2, 3, 4).getCalendar()),
                new Day(new UtcDateTime(2017, 1, 3, 3, 5).getCalendar()));
    }

    @Test
    public void calendar() {
        Day day = Day.parseDay("2017-00-01");
        Day day2 = new Day(day.getCalendar());
        assertEquals(day, day2);
        day2.getCalendar().add(YEAR, 1);
        assertNotEquals(day, day2);
    }

    @Test
    public void add() {
        Day day = Day.parseDay("2017-01-01");
        day.add(2);
        assertEquals("2017-01-03", day.toString());
    }

    @Test
    public void next() {
        Day day = Day.parseDay("2017-01-01");
        Day day2 = day.nextDay();
        assertEquals("2017-01-01", day.toString());
        assertEquals("2017-01-02", day2.toString());
    }

    // won't work if just around midnight
    @Test
    public void today() {
        Day day = new Day();
        assertEquals(0, day.getCalendar().get(Calendar.HOUR));
    }

    // won't work if just around midnight
    @Test
    public void today2() {
        Day day = new Day();
        Calendar calendar = Calendar.getInstance();
        assertEquals(calendar.get(Calendar.DATE), day.getCalendar().get(Calendar.DATE));
    }

    // day from calendar
    @Test
    public void constructorCalendarNonUtcTimeZone() {
        // 2016-12-31T23:30:00.000Z !
        Calendar calendar = CalendarUtils.getCalendar(TimeZone.getTimeZone("Europe/Paris"), 2017, Calendar.JANUARY, 1, 0, 30);
        Day day = new Day(calendar);
        assertEquals("2017-01-01", day.toString());
        assertEquals(Day.parseDay("2017-01-01"), day);
    }

}
