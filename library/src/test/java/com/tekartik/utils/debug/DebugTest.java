package com.tekartik.utils.debug;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by alex on 28/10/16.
 * <p>
 * App global data per target
 */
@RunWith(JUnit4.class)
public class DebugTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() {

    }

    @Test
    public void devLog() throws Exception {

        Debug.devLog("Debug log");
        Debug.devLog("DebugTest", "Debug log with tag");
    }
}
