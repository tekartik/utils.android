package com.tekartik.utils.base64;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
//import com.google.appengine.repackaged.com.google.common.util.Base64;

/**
 * Created by alex on 29/10/16.
 * <p>
 * Checking the codec we use is valid
 */
@RunWith(JUnit4.class)
public class Base64CodecTest {


    @Test
    public void encodeDecode() {
        assertEquals("dGVzdA==", Base64Codec.encodeString("test"));
        assertEquals("test", Base64Codec.decodeString("dGVzdA=="));
    }

    @Test
    public void repackagedBase64() {
        //assertArrayEquals("dGVzdA==".getBytes(), Base64.encodeBase64("test".getBytes()));
        //assertArrayEquals("test".getBytes(), Base64.decodeBase64("dGVzdA=="));
    }
}
