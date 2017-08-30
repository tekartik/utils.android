package com.tekartik.utils.core;

import org.junit.Test;

import static com.tekartik.utils.core.IntegerUtils.nonNull;
import static com.tekartik.utils.core.IntegerUtils.parseInt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntegerUtilsTest {

    @Test
    public void testNoNull() {
        assertEquals(0, nonNull(null));
        assertEquals(1, nonNull(null, 1));
        assertEquals(2, nonNull(null, 2));
        assertEquals(1, nonNull(1, 2));
        assertEquals(1, nonNull(1));
    }

    @Test
    public void testAreEquals() {
        assertTrue(IntegerUtils.areEquals(null, null));
        assertTrue(IntegerUtils.areEquals(123, 123));
        assertFalse(IntegerUtils.areEquals(null, 123));
        assertFalse(IntegerUtils.areEquals(123, null));
    }

    @Test
    public void testParseInt() {
        assertEquals(1, parseInt(1));
        assertEquals(1, parseInt(1L));
        assertEquals(1, parseInt("1"));
        assertEquals(1, parseInt(1.0));
        assertEquals(1, parseInt(1.0f));
        assertEquals(0, parseInt("dummy"));
        assertEquals(1, parseInt("dummy", 1));
    }
}
