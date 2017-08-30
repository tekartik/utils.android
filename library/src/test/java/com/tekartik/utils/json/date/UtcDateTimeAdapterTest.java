package com.tekartik.utils.json.date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tekartik.utils.date.UtcDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class UtcDateTimeAdapterTest {

    static public Gson gson;

    static public Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(UtcDateTime.class, new UtcDateTimeAdapter())
                    .create();
        }
        return gson;
    }

    @Test
    public void utcDateTime() throws Exception {
        Gson gson = getGson();

        // Check AM/PM
        UtcDateTimeTest test = new UtcDateTimeTest();
        test.date = UtcDateTime.parse("2000-01-01T13:32:00.000Z");
        String text = gson.toJson(test);
        assertEquals("{\"date\":\"2000-01-01T13:32:00.000Z\"}", text);

        test = gson.fromJson(text, UtcDateTimeTest.class);
        assertEquals("2000-01-01T13:32:00.000Z", test.date.toString());

        test = gson.fromJson("{}", UtcDateTimeTest.class);
        assertNull(test.date);

    }

    static class UtcDateTimeTest {
        UtcDateTime date;
    }
}