package com.tekartik.android.utils.test;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.tekartik.android.utils.ResUtils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by alex on 30/08/16.
 * <p>
 * test fetching data
 */
@RunWith(AndroidJUnit4.class)
public class ResUtilsTest {


    static private Context getContext() throws PackageManager.NameNotFoundException {
        Context context = ApplicationProvider.getApplicationContext();
        return context;
        // return InstrumentationRegistry.getContext();
        // return ApplicationProvider.getApplicationContext(); // InstrumentationRegistry.getContext();
        //return PackageManager
    }

    static private Resources getResources() throws PackageManager.NameNotFoundException {
        // failing! return getContext().getResources();
        //return InstrumentationRegistry.getInstrumentation().getContext().getResources();
        return InstrumentationRegistry.getContext().getResources();
        // return getContext().getPackageManager().getResourcesForApplication("com.tekartik.android.utils.libtest");
        // return InstrumentationRegistry.getContext();
        // return ApplicationProvider.getApplicationContext(); // InstrumentationRegistry.getContext();
        //return PackageManager
    }

    @Test
    public void read() throws Exception {
        Assert.assertEquals("simple_content", ResUtils.loadRawStringId(getResources(), com.tekartik.android.utils.libtest.test.R.raw.test));
    }
}
