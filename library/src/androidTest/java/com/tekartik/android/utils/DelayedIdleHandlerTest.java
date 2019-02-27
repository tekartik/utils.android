package com.tekartik.android.utils;

import android.os.Looper;
import androidx.test.runner.AndroidJUnit4;

import com.tekartik.android.utils.handler.DelayedHandler;
import com.tekartik.android.utils.handler.DelayedHandlerListener;
import com.tekartik.android.utils.handler.DelayedIdleHandler;
import com.tekartik.utils.async.Completer;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 30/08/16.
 * <p>
 * test fetching data
 */
@RunWith(AndroidJUnit4.class)
public class DelayedIdleHandlerTest {


    static class Data {
        boolean handled;
        Completer.Void completer;
    }

    static class DelayerHandlerBgTask extends BgTask {

        public DelayedHandler delayedHandler;
        public Data data = new Data();
        Completer.Void startCompleter = new Completer.Void();
        Looper looper;

        @Override
        protected void doInBackground() throws Exception {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            looper = Looper.myLooper();
            delayedHandler = new DelayedIdleHandler(100, new DelayedHandlerListener() {
                @Override
                public void onHandle() {
                    data.handled = true;
                    if (data.completer != null) {
                        data.completer.complete();
                    }
                }
            });
            startCompleter.complete();
            Looper.loop();
        }
    }
    @Test
    public void testBgDelayedHandler() throws ExecutionException, InterruptedException, TimeoutException {
        DelayerHandlerBgTask task = new DelayerHandlerBgTask();
        Data data =task.data;
        assertFalse(data.handled);
        task.execute();

        task.startCompleter.getFuture().get();
        DelayedHandler delayedHandler = task.delayedHandler;

        // Get immediate should fail
        data.completer = new Completer.Void();
        assertFalse(data.handled);
        data.handled = false;
        data.completer = new Completer.Void();
        delayedHandler.trigger();
        try {
            data.completer.getFuture().get(10);
        } catch (TimeoutException e) {
        }
        // trigger again
        delayedHandler.trigger();

        assertFalse(data.handled);
        data.completer.getFuture().get(1000);
        assertTrue(data.handled);

        task.looper.quit();
        task.get();
    }
}
