package com.tekartik.utils.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class YearTest {

    @Test
    public void parseDate() {
        assertEquals("0001", Year.parseYear("1").toString());
        assertEquals("2017", Year.parseYear("2017").toString());
        assertEquals("2017", Year.parseYear("2017-01-01").toString());
        assertEquals("2017", Year.parseYear("2017-01").toString());
        //assertEquals("2017-01", Year.parseYear("2017-01-01").toString());
        Year Year1 = Year.parseYear("2017-01");
        Year Year2 = Year.parseYear("2017-01");
        assertNotNull(Year1);
        assertEquals(Year1, Year2);

        assertEquals(new Year(new UtcDateTime(2017, 1, 2, 3, 4).getCalendar()),
                new Year(new UtcDateTime(2017, 1, 3, 3, 5).getCalendar()));
        assertNotEquals(new Year(new UtcDateTime(2017, 1, 2, 3, 4).getCalendar()),
                new Year(new UtcDateTime(2016, 1, 2, 3, 5).getCalendar()));
    }
}
