package com.tekartik.utils.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.TimeZone;

import static com.tekartik.utils.date.DateTime.compare;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class DateTimeTest {

    @Test
    public void dateTimeToString() {
        assertEquals("2017-02-02T03:04:05.006+0000", new DateTime(Utc.timeZone, 2017, 1, 2, 3, 4, 5, 6).toString());
        assertEquals("2017-02-02T03:04:05.006+0100", new DateTime(TimeZone.getTimeZone("Europe/Paris"), 2017, 1, 2, 3, 4, 5, 6).toString());
    }

    @Test
    public void testParse() {
        assertEquals("2017-02-13T10:14:34.123+0100", DateTime.parse("2017-02-13T10:14:34.123+0100").toString());
        assertEquals(1486977274123L, DateTime.parse("2017-02-13T10:14:34.123+0100").getCalendar().getTime().getTime());
    }

    @Test
    public void testEquals() {
        assertEquals(DateTime.parse("2017-02-13T11:14:34.123+0200").getCalendar().getTime().getTime(), DateTime.parse("2017-02-13T10:14:34.123+0100").getCalendar().getTime().getTime());
        assertEquals(DateTime.parse("2017-02-13T11:14:34.123+0200"), DateTime.parse("2017-02-13T10:14:34.123+0100"));

    }

    @Test
    public void calendarCloned() {
        Calendar calendar = CalendarUtils.getCalendar(Utc.timeZone, 2017, 1, 2);
        DateTime dateTime1 = new DateTime(calendar);
        DateTime dateTime2 = new DateTime(calendar);
        assertEquals(dateTime1, dateTime2);
        dateTime2.getCalendar().add(Calendar.DATE, 1);
        assertNotEquals(dateTime1, dateTime2);
    }

    @Test
    public void fromDate() {
        java.util.Date date = new java.util.Date(12345678901234L);
        DateTime dateTime = new DateTime(date);
        assertEquals("2361-03-21T20:15:01.234+0100", dateTime.toString());
    }

    @Test
    public void fromMillis() {
        DateTime dateTime = new DateTime(12345678901234L);
        assertEquals("2361-03-21T20:15:01.234+0100", dateTime.toString());
    }

    @Test
    public void fromUtcDateTime() {
        DateTime dateTime = new UtcDateTime(12345678901234L);
        dateTime = new DateTime(dateTime);
        assertEquals("2361-03-21T20:15:01.234+0100", dateTime.toString());
    }

    @Test
    public void fromDateTime() {
        DateTime dateTime = new DateTime(12345678901234L);
        dateTime = new DateTime(dateTime);
        assertEquals("2361-03-21T20:15:01.234+0100", dateTime.toString());
    }

    @Test
    public void getMillis() {
        java.util.Date date = new java.util.Date(12345678901234L);
        DateTime dateTime = new DateTime(date);

        assertEquals(date.getTime(), dateTime.getMillis());
    }

    @Test
    public void testCompare() {
        DateTime dateTime1 = new DateTime(null, 2017, 1, 1);
        DateTime dateTime2 = new DateTime(null, 2017, 1, 1);

        assertFalse(dateTime1.after(dateTime2));
        assertFalse(dateTime2.after(dateTime1));
        assertEquals(0, compare(dateTime1, dateTime2));
        dateTime2 = new DateTime(null, 2017, 1, 2);
        assertEquals(-1, compare(dateTime1, dateTime2));
        assertTrue(dateTime1.before(dateTime2));
        assertFalse(dateTime1.after(dateTime2));
        assertEquals(1, compare(dateTime2, dateTime1));
        assertTrue(dateTime2.after(dateTime1));
        assertFalse(dateTime2.before(dateTime1));

    }
}
