package com.tekartik.utils.date;

import org.junit.Test;

import static com.tekartik.utils.date.DateUtils.elapsed;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateUtilsTest {

    @Test
    public void testElapsed() {
        assertTrue(elapsed(null, null, 0));
        assertTrue(elapsed(null, new java.util.Date(1L), 0));
        assertTrue(elapsed(new java.util.Date(1L), null, 0));
        assertTrue(elapsed(new java.util.Date(1L), new java.util.Date(3L), 2L));
        assertTrue(elapsed(new java.util.Date(3L), new java.util.Date(1L), 2L));
        assertFalse(elapsed(new java.util.Date(3L), new java.util.Date(1L), 3));
        assertFalse(elapsed(new java.util.Date(1L), new java.util.Date(3L), 3));
        assertFalse(elapsed(new java.util.Date(1000000000000L), new java.util.Date(2000000000000L), 1000000000001L));
        assertTrue(elapsed(new java.util.Date(1000000000000L), new java.util.Date(2000000000000L), 1000000000000L));
    }
}
