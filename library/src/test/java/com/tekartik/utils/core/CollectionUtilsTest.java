package com.tekartik.utils.core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tekartik.utils.core.CollectionUtils.getSize;
import static com.tekartik.utils.core.CollectionUtils.isEmpty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionUtilsTest {

    @Test
    public void testIsEmpty() {
        assertTrue(isEmpty(null));
        assertTrue(isEmpty(new ArrayList<>()));
        assertFalse(isEmpty(Arrays.asList("test")));
    }

    @Test
    public void testGetSize() {
        assertEquals(0, getSize(null));
        assertEquals(0, getSize(new ArrayList<>()));
        assertEquals(1, getSize((Arrays.asList("test"))));
        assertEquals(2, getSize((Arrays.asList("test", null))));
    }
}
