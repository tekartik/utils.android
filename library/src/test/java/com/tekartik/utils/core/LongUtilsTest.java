package com.tekartik.utils.core;

import org.junit.Test;

import static com.tekartik.utils.core.LongUtils.nonNull;
import static com.tekartik.utils.core.LongUtils.parseLong;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LongUtilsTest {

    @Test
    public void testNonNull() {
        assertEquals(0, nonNull(null, 0));
        assertEquals(1, nonNull(null, 1));
        assertEquals(2, nonNull(null, 2));
        assertEquals(1, nonNull(1L, 2));
        assertEquals(1, nonNull(1L, 0));
    }

    @Test
    public void testAreEquals() {
        assertTrue(LongUtils.areEquals(null, null));
        assertTrue(LongUtils.areEquals(123L, 123L));
        assertFalse(LongUtils.areEquals(null, 123L));
        assertFalse(LongUtils.areEquals(123L, null));
    }

    @Test
    public void testParseLong() {
        assertEquals(1, parseLong(1).longValue());
        assertEquals(1, parseLong(1L).longValue());
        assertEquals(1, parseLong("1").longValue());
        assertEquals(1, parseLong(1.0).longValue());
        assertEquals(1, parseLong(1.0f).longValue());
        assertEquals(null, parseLong("dummy"));
        assertEquals(1, parseLong("dummy", 1));
    }
}
