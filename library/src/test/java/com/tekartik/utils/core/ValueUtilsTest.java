package com.tekartik.utils.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValueUtilsTest {

    @Test
    public void testAreEquals() {
        assertTrue(ValueUtils.areEquals(null, null));
        assertTrue(ValueUtils.areEquals("123", "123"));
        assertFalse(ValueUtils.areEquals(null, "123"));
        assertFalse(ValueUtils.areEquals("123", null));
    }

    @Test
    public void testNonNull() {
        assertEquals(null, ValueUtils.nonNull(null, null));
        assertEquals(1, ValueUtils.nonNull(null, 1).intValue());
        assertEquals(1, ValueUtils.nonNull(1, null).intValue());
        assertEquals(1, ValueUtils.nonNull(null, 1L).longValue());
        assertEquals("test", ValueUtils.nonNull(null, "test"));
        assertEquals("test", ValueUtils.nonNull("test", null));
        assertEquals("test", ValueUtils.nonNull("test", "other"));
    }
}
