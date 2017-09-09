package com.tekartik.android.utils.test;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.tekartik.android.utils.BgTask;
import com.tekartik.utils.async.Completer;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 30/08/16.
 * <p>
 * test fetching data
 */
@RunWith(AndroidJUnit4.class)
public class CompleterTest {


    static private Context getContext() {
        return InstrumentationRegistry.getContext();
    }

    class SimpleData {
        Completer<Void> completer = new Completer<>();
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
            data.completer.complete(null);
        }
    }

    @Test
    public void complete() throws Exception {

        SimpleData data = new SimpleData();
        new SimpleBgTask(data).execute();
        assertFalse(data.doInBackgroundDone);
        assertFalse(data.onPostExecuteDone);

        data.completer.getFuture().get(30000);


        assertTrue(data.doInBackgroundDone);
        assertTrue(data.onPostExecuteDone);
    }


}
