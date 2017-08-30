package com.tekartik.utils.date;

import org.junit.Test;

import java.util.Calendar;
import java.util.TimeZone;

import static com.tekartik.utils.date.CalendarUtils.calendarCopyDateTime;
import static com.tekartik.utils.date.CalendarUtils.clearTime;
import static com.tekartik.utils.date.CalendarUtils.getCalendar;
import static com.tekartik.utils.date.CalendarUtils.getDiffYears;
import static com.tekartik.utils.date.CalendarUtils.utcFormat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CalendarUtilsTest {

    @Test
    public void testUtcFormat() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1490960037162L);
        assertEquals("2017-03-31T11:33:57.162Z", utcFormat(calendar));

        assertEquals("2017-02-02T03:04:05.006Z", utcFormat(getCalendar(Utc.timeZone, 2017, 1, 2, 3, 4, 5, 6)));

    }

    @Test
    public void testGetCalendar() {
        Calendar calendar = getCalendar(null);
        assertEquals(calendar.getTimeZone(), TimeZone.getDefault());
        calendar = getCalendar(Utc.timeZone, 1490960037162L);
        assertEquals("2017-03-31T11:33:57.162Z", utcFormat(calendar));
    }

    @Test
    public void testClone() {
        Calendar calendar1 = getCalendar(Utc.timeZone, 2017, 0, 1);
        Calendar calendar2 = CalendarUtils.clone(calendar1);
        assertEquals(0, calendar1.compareTo(calendar2));
        calendar2.add(Calendar.YEAR, 1);
        assertTrue(calendar1.compareTo(calendar2) < 0);
        //assertEquals(utcFormat(calendar1, calendar2)
    }

    @Test
    public void testCopy() {
        Calendar calendar1 = getCalendar(Utc.timeZone, 2017, 1, 2, 3, 4, 5, 6);
        Calendar calendar2 = getCalendar(Utc.timeZone);
        calendarCopyDateTime(calendar1, calendar2);
        assertEquals(calendar1, calendar2);

        calendar2 = getCalendar(TimeZone.getTimeZone("Europe/Paris"));
        calendarCopyDateTime(calendar1, calendar2);
        assertNotEquals(calendar1, calendar2);
    }

    @Test
    public void testClearTime() throws Exception {
        assertTrue(utcFormat(clearTime(Calendar.getInstance(Utc.timeZone))), utcFormat(clearTime(Calendar.getInstance(Utc.timeZone))).endsWith("00:00:00.000Z"));
        assertTrue(utcFormat(clearTime(Calendar.getInstance())), utcFormat(clearTime(Calendar.getInstance())).endsWith(":00.000Z"));
    }

    @Test
    public void getDiffYear() throws Exception {
        assertEquals(10, getDiffYears(getCalendar(null, 2008, 0, 1),
                getCalendar(null, 2018, 4, 1)));
        assertEquals(9, getDiffYears(getCalendar(null, 2008, 8, 1),
                getCalendar(null, 2018, 4, 1)));
        assertEquals(1, getDiffYears(getCalendar(null, 2017, 0, 1),
                getCalendar(null, 2018, 0, 1)));
        assertEquals(0, getDiffYears(getCalendar(null, 2017, 0, 1),
                getCalendar(null, 2017, 11, 31)));

    }
}
