package com.tekartik.utils.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class BooleanUtilsTest {

    @Test
    public void isTrue() {
        assertTrue(BooleanUtils.isTrue(true));
        assertTrue(BooleanUtils.isTrue(Boolean.TRUE));
        assertFalse(BooleanUtils.isTrue(false));
        assertFalse(BooleanUtils.isTrue(Boolean.FALSE));
        assertFalse(BooleanUtils.isTrue(null));
    }

    @Test
    public void trueOrNull() {
        assertTrue(BooleanUtils.trueOrNull(true));
        assertTrue(BooleanUtils.trueOrNull(Boolean.TRUE));
        assertNull(BooleanUtils.trueOrNull(false));
        assertNull(BooleanUtils.trueOrNull(Boolean.FALSE));
        assertNull(BooleanUtils.trueOrNull(null));
    }
}
