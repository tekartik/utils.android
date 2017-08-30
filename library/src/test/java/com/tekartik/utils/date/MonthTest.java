package com.tekartik.utils.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class MonthTest {

    @Test
    public void parseDate() {
        assertEquals(null, Month.parseMonth("2017"));
        assertEquals("2017-01", Month.parseMonth("2017-01-01").toString());
        Month Month1 = Month.parseMonth("2017-01");
        Month Month2 = Month.parseMonth("2017-01");
        assertEquals("2017-01", Month.parseMonth("2017-01").toString());
        assertNotNull(Month1);
        assertEquals(Month1, Month2);

        assertEquals(new Month(new UtcDateTime(2017, 1, 2, 3, 4).getCalendar()),
                new Month(new UtcDateTime(2017, 1, 3, 3, 5).getCalendar()));
        assertNotEquals(new Month(new UtcDateTime(2017, 2, 2, 3, 4).getCalendar()),
                new Month(new UtcDateTime(2017, 1, 2, 3, 3).getCalendar()));
    }
}
