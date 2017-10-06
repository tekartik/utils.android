package com.tekartik.utils.core;

import org.junit.Test;

import static com.tekartik.utils.core.TimestampUtils.elapsed;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimestampUtilsTest {

    @Test
    public void testElapsed() {
        assertTrue(elapsed(null, null, 0));
        assertTrue(elapsed(null, 1L, 0));
        assertTrue(elapsed(1L, null, 0));
        assertTrue(elapsed(1L, 3L, 2L));
        assertTrue(elapsed(3L, 1L, 2L));
        assertFalse(elapsed(3L, 1L, 3));
        assertFalse(elapsed(1L, 3L, 3));
        assertFalse(elapsed(1000000000000L, 2000000000000L, 1000000000001L));
        assertTrue(elapsed(1000000000000L, 2000000000000L, 1000000000000L));
    }
}
