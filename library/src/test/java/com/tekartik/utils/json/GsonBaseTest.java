package com.tekartik.utils.json;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class GsonBaseTest {

    @Test
    public void testEquals() {
        GsonBase.assertJsonEquals(null, null);
        GsonBase.assertJsonEquals("", null);
        GsonBase.assertJsonEquals("{}", " {}");
        GsonBase.assertJsonEquals("{\"test\":1}", " {\"test\":1}");
        GsonBase.assertJsonEquals(" []", " []");
        try {
            GsonBase.assertJsonEquals("{}", " []");
            fail("_");
        } catch (AssertionError e) {
            assertNotEquals("_", e.getMessage());

        }
        try {
            GsonBase.assertJsonEquals("{\"test\":1}", " {}");
            fail("_");
        } catch (AssertionError e) {
            assertNotEquals("_", e.getMessage());

        }
    }
}
