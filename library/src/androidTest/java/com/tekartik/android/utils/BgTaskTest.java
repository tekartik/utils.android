package com.tekartik.android.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.CallSuper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.tekartik.android.utils.BgTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 30/08/16.
 * <p>
 * test fetching data
 */
@RunWith(AndroidJUnit4.class)
public class BgTaskTest {


    static private Context getContext() {
        return InstrumentationRegistry.getContext();
    }

    class SimpleData {
        boolean doInBackgroundDone = false;
        boolean onPostExecuteDone = false;
    }

    class SimpleBgTask extends BgTask {
        final SimpleData data;

        SimpleBgTask(SimpleData data) {
            this.data = data;
        }

        @Override
        protected void doInBackground() throws Exception {
            data.doInBackgroundDone = true;
        }

        @Override
        @CallSuper
        protected void onPostExecute() {
            data.onPostExecuteDone = true;
        }
    }

    @Test
    public void simple() throws Exception {

        SimpleData data = new SimpleData();
        BgTask bgTask = new SimpleBgTask(data).execute();
        assertFalse(data.doInBackgroundDone);
        assertFalse(data.onPostExecuteDone);

        //signal.await(30, TimeUnit.SECONDS);
        bgTask.get(30, TimeUnit.SECONDS);

        assertTrue(data.doInBackgroundDone);
        assertTrue(data.onPostExecuteDone);
    }

    @Test
    public void countDownLatch() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        SimpleData data = new SimpleData();
        new SimpleBgTask(data) {
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

    @Test
    public void asyncTaskExp() throws ExecutionException, InterruptedException {
        final SimpleData data = new SimpleData();

        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                data.doInBackgroundDone = true;
                return null;
            }
        }.execute();
        assertFalse(data.doInBackgroundDone);
        asyncTask.get();
        assertTrue(data.doInBackgroundDone);


    }

    @Test
    public void asyncTaskExp2() throws ExecutionException, InterruptedException {
        final SimpleData data = new SimpleData();

        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                data.doInBackgroundDone = true;
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                data.onPostExecuteDone = true;
            }
        }.execute();
        assertFalse(data.doInBackgroundDone);
        assertFalse(data.onPostExecuteDone);
        asyncTask.get();
        assertTrue(data.doInBackgroundDone);
        assertTrue(data.onPostExecuteDone);


    }
}
