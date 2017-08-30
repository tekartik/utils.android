package com.tekartik.utils.json.date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tekartik.utils.date.DateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class DateTimeAdapterTest {

    static public Gson gson;

    static public Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(DateTime.class, new DateTimeAdapter())
                    .create();
        }
        return gson;
    }

    @Test
    public void utcDateTime() throws Exception {
        Gson gson = getGson();

        // Check AM/PM
        DateTimeTest test = new DateTimeTest();
        test.date = DateTime.parse("2000-01-01T13:32:00.000+0100");
        String text = gson.toJson(test);
        assertEquals("{\"date\":\"2000-01-01T13:32:00.000+0100\"}", text);

        test = gson.fromJson(text, DateTimeTest.class);
        assertEquals("2000-01-01T13:32:00.000+0100", test.date.toString());

        test = gson.fromJson("{}", DateTimeTest.class);
        assertNull(test.date);

    }

    static class DateTimeTest {
        DateTime date;
    }
}