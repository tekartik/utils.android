package com.tekartik.utils.security;

import com.tekartik.utils.sequence.SequenceValidator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

public class HashUtilsTest {
    @Test
    public void md5() throws Exception {
        assertNull(HashUtils.md5(null));
        assertEquals("d41d8cd98f0b24e980998ecf8427e", HashUtils.md5(""));
        assertEquals("98f6bcd4621d373cade4e832627b4f6", HashUtils.md5("test"));
    }

}