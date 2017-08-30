package com.tekartik.utils.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class DateParserTest {

    @Test
    public void parseDate() {
        assertEquals("2017", DateParser.parse("2017").toString());
        assertEquals("2017-01", DateParser.parse("2017-01").toString());
        assertEquals("2017-01-01", DateParser.parse("2017-01-01").toString());
    }
}
