package com.tekartik.utils.core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tekartik.utils.core.ListUtils.get;
import static com.tekartik.utils.core.ListUtils.isEmpty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ListUtilsTest {

    @Test
    public void testGet() {
        assertNull(get(null, 0));
        assertNull(get(null, -1));
        assertNull(get(null, 1));
        assertNull(get(new ArrayList<String>(), -1));
        assertNull(get(new ArrayList<String>(), 0));
        assertNull(get(new ArrayList<String>(), 1));

        List<String> list = Arrays.asList("test");
        assertEquals("test", get(list, 0));
        assertNull(get(list, -1));
        assertNull(get(list, 1));
        list = Arrays.asList("test1", null, "test2");
        assertEquals("test1", get(list, 0));
        assertNull(get(list, -1));
        assertNull(get(list, 1));
        assertEquals("test2", get(list, 2));
        assertNull(get(list, 3));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(isEmpty(null));
        assertTrue(isEmpty(new ArrayList<>()));
        assertFalse(isEmpty(Arrays.asList(1)));
    }

}
