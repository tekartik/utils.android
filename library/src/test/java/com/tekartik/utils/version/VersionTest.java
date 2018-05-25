package com.tekartik.utils.version;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * test Version
 */
public class VersionTest {

    @Test
    public void parse() {
        Version version = Version.parse("1.2.3");
        assertEquals("1.2.3", version.toString());

        version = Version.parse("1.2");
        assertEquals("1.2.0", version.toString());

        version = Version.parse("1");
        assertEquals("1.0.0", version.toString());

        version = Version.parse("v1.7.4+2-other");
        assertEquals("1.7.4", version.toString());
        version = Version.parse("1.7.4-dev.1");
        assertEquals("1.7.4", version.toString());

        assertNull(Version.parse(null));
        assertNull(Version.parse(""));
        assertNull(Version.parse("_"));
    }
}


