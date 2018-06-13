package com.tekartik.utils.core;

import org.junit.Assert;
import org.junit.Test;

import static com.tekartik.utils.core.StringUtils.capitalizeFirstLetter;
import static com.tekartik.utils.core.StringUtils.nonEmpty;
import static com.tekartik.utils.core.StringUtils.nonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StringUtilsTest {

    @Test
    public void testFill() {
        Assert.assertEquals("012345", StringUtils.fill(6));
        assertEquals("", StringUtils.fill(0));
        assertEquals("01234567890", StringUtils.fill(11));
    }

    @Test
    public void testTruncate() {
        assertNull(StringUtils.truncate(null, 25));
        String base = StringUtils.fill(10);
        assertEquals("012345", StringUtils.truncate(base, 6));
        assertEquals("", StringUtils.truncate(base, 0));
        assertEquals("0123456789", StringUtils.truncate(base, 11));
    }

    @Test
    public void testNonNull() {
        assertEquals("", nonNull(null));
        assertEquals("_", nonNull("_"));
    }

    @Test
    public void testNonEmpty() {
        assertEquals(null, nonEmpty(null));
        assertEquals(null, nonEmpty(""));
        assertEquals("a", nonEmpty("a"));
        assertEquals("b", nonEmpty(null, "b"));
        assertEquals("b", nonEmpty("", "b"));
        assertEquals("a", nonEmpty("a", "b"));
    }

    @Test
    public void testCapitalizeFirstLetter() {
        assertEquals(null, capitalizeFirstLetter(null));
        assertEquals("", capitalizeFirstLetter(""));
        assertEquals("A", capitalizeFirstLetter("A"));
        assertEquals("A", capitalizeFirstLetter("a"));
        assertEquals("À", capitalizeFirstLetter("à"));
        assertEquals("Àb", capitalizeFirstLetter("àb"));
        assertEquals("Àb c", capitalizeFirstLetter("àb c"));
    }
}
