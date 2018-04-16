package com.tekartik.utils.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class UtcDateTimeTest {

    @Test
    public void dateTimeToString() {
        UtcDateTime utcDateTime = new UtcDateTime(2017, 1, 2, 3, 4, 5, 6);
        //devLog(utcFormat(utcDateTime.getCalendar()));
        //devLog(utcDateTime.toString());

        assertEquals("2017-02-02T03:04:05.006Z", utcDateTime
                .toString());
    }

    @Test
    public void fromDate() {
        Date date = new Date(12345678901234L);
        UtcDateTime dateTime = new UtcDateTime(date);
        assertEquals("2361-03-21T19:15:01.234Z", dateTime.toString());

    }

    @Test
    public void fromMillis() {
        UtcDateTime dateTime = new UtcDateTime(12345678901234L);
        assertEquals("2361-03-21T19:15:01.234Z", dateTime.toString());
    }

    @Test
    public void fromUtcDateTime() {
        DateTime dateTime = new UtcDateTime(12345678901234L);
        dateTime = new UtcDateTime(dateTime);
        assertEquals("2361-03-21T19:15:01.234Z", dateTime.toString());
    }

    @Test
    public void fromDateTime() {
        DateTime dateTime = new DateTime(12345678901234L);
        dateTime = new UtcDateTime(dateTime);
        assertEquals("2361-03-21T19:15:01.234Z", dateTime.toString());
    }

    @Test
    public void parse() throws Exception {

        assertNull(UtcDateTime.parse(null));
        //assertNull(UtcDateTime.parse(""));
        assertEquals("2017-02-13T10:14:34.123Z", UtcDateTime.parse("2017-02-13T10:14:34.123Z").toString());
        UtcDateTime utcDateTime = new UtcDateTime();
        assertEquals(utcDateTime, UtcDateTime.parse(utcDateTime.toString()));
    }
}
