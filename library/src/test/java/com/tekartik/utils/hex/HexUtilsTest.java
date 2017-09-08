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

    @Test
    public void parseHexString() {

        assertArrayEquals(new byte[] {0x01, (byte) 0x83, 0x3d, 0x79}, HexUtils.parse("01 83 3d 79"));
        assertArrayEquals(new byte[] {0x01, (byte) 0x83}, HexUtils.parse("0183"));
        assertArrayEquals(new byte[] {0x01, (byte) 0x83, 0x3d, 0x79, (byte) 0xFF}, HexUtils.parse("01 83 3d79 FF"));
        assertArrayEquals(new byte[] {0x01}, HexUtils.parse("0x01"));
        assertArrayEquals(new byte[] {(byte) 0xFF, (byte) 0xFE, (byte) 0xED, (byte) 0xDC}, HexUtils.parse("0xFFFE ED xDC"));
        /*
        expect(parseHexString("0183"), [0x01, 0x83]);
        expect(parseHexString("01 83 3d79 FF"), [0x01, 0x83, 0x3d, 0x79, 0xFF]);
        expect(parseHexString("0x01"), [0x01]);
        expect(parseHexString("0xFFFE ED xDC"), [0xFF, 0xFE, 0xED, 0xDC]);
        // parseHexString
    });*/
    }
}
