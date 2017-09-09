package com.tekartik.android.utils.test;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.RawRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.tekartik.android.utils.ResUtils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.tekartik.utils.debug.Debug.devLog;
import static org.junit.Assert.assertEquals;

/**
 * Created by alex on 30/08/16.
 * <p>
 * test fetching data
 */
@RunWith(AndroidJUnit4.class)
public class ResUtilsTest {


    static private Context getContext() {
        return InstrumentationRegistry.getContext();
    }

    @Test
    public void read() throws Exception {
        Assert.assertEquals("simple_content", ResUtils.loadRawStringId(getContext().getResources(), com.tekartik.android.utils.test.test.R.raw.test));
    }
}
