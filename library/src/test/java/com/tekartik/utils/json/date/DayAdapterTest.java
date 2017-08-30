package com.tekartik.utils.json.date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tekartik.utils.date.CalendarUtils;
import com.tekartik.utils.date.Day;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class DayAdapterTest {

    static public Gson gson;

    static public Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Day.class, new DayAdapter())
                    .create();
        }
        return gson;
    }

    @Test
    public void day() throws Exception {
        Gson gson = getGson();
        Day day = gson.fromJson("2017-01-01", Day.class);
        assertEquals("2017-01-01", day.toString());

        Calendar calendar = day.getCalendar();
        assertEquals(2017, calendar.get(Calendar.YEAR));
        assertEquals(0, calendar.get(Calendar.MONTH));
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(0, calendar.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, calendar.get(Calendar.MINUTE));

        String text = gson.toJson(day);
        assertEquals("\"2017-01-01\"", text);

        DayTest test = new DayTest();
        test.day = Day.parseDay("2017-01-01");
        assertEquals("{\"day\":\"2017-01-01\"}", gson.toJson(test));

        test = gson.fromJson("{}", DayTest.class);
        assertNull(test.day);

        Calendar utcNow = CalendarUtils.getNowToUtcCalendarSameTime();
        test.day = new Day(utcNow);
        text = gson.toJson(test);
        test = gson.fromJson(text, DayTest.class);
        assertEquals(text, gson.toJson(test));
    }


    static class DayTest {
        Day day;
    }
}