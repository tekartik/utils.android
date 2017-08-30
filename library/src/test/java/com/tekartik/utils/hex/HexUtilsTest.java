package com.tekartik.utils.hex;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by alex on 28/10/16.
 * <p>
 * App global data per target
 */
@RunWith(JUnit4.class)
public class HexUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() {

    }

    @Test
    public void byteArrayToHexString() throws Exception {
        assertEquals(null, HexUtils.byteArrayToHexString(null));
        assertEquals("", HexUtils.byteArrayToHexString(new byte[]{}));
        assertEquals("01", HexUtils.byteArrayToHexString(new byte[]{0x01}));
        assertEquals("FF", HexUtils.byteArrayToHexString(new byte[]{(byte) 0xFF}));
        assertEquals("01FF", HexUtils.byteArrayToHexString(new byte[]{0x01, (byte) 0xFF}));
    }

    @Test
    public void hexStringToByteArray() throws Exception {
        assertEquals(null, HexUtils.hexStringToByteArray(null));
        assertEquals(0, HexUtils.hexStringToByteArray("").length);
        assertArrayEquals(new byte[]{0x01}, HexUtils.hexStringToByteArray("01"));
        assertArrayEquals(new byte[]{(byte) 0xFF}, HexUtils.hexStringToByteArray("FF"));
        assertArrayEquals(new byte[]{(byte) 0xFF}, HexUtils.hexStringToByteArray("ff"));
        assertArrayEquals(new byte[]{0x01, (byte) 0xFF}, HexUtils.hexStringToByteArray("01FF"));
    }
}
