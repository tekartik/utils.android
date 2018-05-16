package com.tekartik.utils.security;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

public class HashUtilsTest {
    @Test
    public void md5() {
        assertNull(HashUtils.md5(null));
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", HashUtils.md5(""));
        assertEquals("098f6bcd4621d373cade4e832627b4f6", HashUtils.md5("test"));
    }

    @Test
    public void sha1() {
        assertNull(HashUtils.sha1(null));
        assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", HashUtils.sha1(""));
        assertEquals("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", HashUtils.sha1("test"));
    }

}