package com.tekartik.utils.core;

import org.junit.Test;

import static com.tekartik.utils.core.ValueUtils.areEquals;
import static com.tekartik.utils.core.ValueUtils.areEqualsNonNull;
import static com.tekartik.utils.core.ValueUtils.nonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValueUtilsTest {

    @Test
    public void testAreEquals() {
        assertTrue(areEquals(null, null));
        assertTrue(areEquals("123", "123"));
        assertTrue(areEquals(1L, 1L));
        assertFalse(areEquals(null, "123"));
        assertFalse(areEquals("123", null));
        // ! not same type
        assertFalse(areEquals((Long) 1L, (Integer) 1));
    }

    @Test
    public void testAreEqualsNonNull() {
        assertFalse(areEqualsNonNull(null, null));
        assertFalse(areEqualsNonNull(null, "123"));
        assertFalse(areEqualsNonNull("123", null));
        assertTrue(areEqualsNonNull("123", "123"));
        assertTrue(areEqualsNonNull(1L, 1L));
        // ! not same type
        assertFalse(areEqualsNonNull((Long) 1L, (Integer) 1));
    }

    @Test
    public void testNonNull() {
        assertEquals(null, nonNull(null, null));
        assertEquals(1, nonNull(null, 1).intValue());
        assertEquals(1, nonNull(1, null).intValue());
        assertEquals(1, nonNull(null, 1L).longValue());
        assertEquals("test", nonNull(null, "test"));
        assertEquals("test", nonNull("test", null));
        assertEquals("test", nonNull("test", "other"));
    }
}
