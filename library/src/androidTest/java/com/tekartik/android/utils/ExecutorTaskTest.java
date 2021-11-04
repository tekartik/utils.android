package com.tekartik.android.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.CallSuper;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by alex on 30/08/16.
 * <p>
 * test fetching data
 */
@RunWith(AndroidJUnit4.class)
public class ExecutorTaskTest {


    static private Context getContext() {
        return ApplicationProvider.getApplicationContext();
    }

    class SimpleData {
        boolean doInBackgroundDone = false;
        boolean onPostExecuteDone = false;
    }

    class SimpleExecutorTask extends ExecutorTask {
        final SimpleData data;

        SimpleExecutorTask(SimpleData data) {
            this.data = data;
        }

        @Override
        protected void doInBackground() throws Exception {
            Log.d("/test", "do");
            data.doInBackgroundDone = true;
        }

        @Override
        @CallSuper
        protected void onPostExecute() {
            data.onPostExecuteDone = true;
        }
    }

      @Test
    public void countDownLatch() throws Exception {

        final CountDownLatch signal = new CountDownLatch(1);
        SimpleData data = new SimpleData();
        new SimpleExecutorTask(data) {
            protected void onPostExecute() {

                super.onPostExecute();
                signal.countDown();
            }
        }.execute();
        assertFalse(data.doInBackgroundDone);
        assertFalse(data.onPostExecuteDone);

        signal.await(30, TimeUnit.SECONDS);

        assertTrue(data.doInBackgroundDone);
        assertTrue(data.onPostExecuteDone);
    }

}
