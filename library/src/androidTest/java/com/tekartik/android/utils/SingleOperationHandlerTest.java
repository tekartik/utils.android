package com.tekartik.android.utils;

import android.os.Looper;

import com.tekartik.android.utils.handler.SingleOperationHandler;
import com.tekartik.utils.async.Completer;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import androidx.test.runner.AndroidJUnit4;

import static com.tekartik.utils.debug.Debug.devLog;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by alex on 30/08/16.
 * <p>
 * test fetching data
 */
@RunWith(AndroidJUnit4.class)
public class SingleOperationHandlerTest {

/*
    @Test
    public void testSingleOperationHandler() throws ExecutionException, InterruptedException, TimeoutException {
        final DelayerHandlerBgTask task = new DelayerHandlerBgTask();
        final Data data = task.data;
        assertEquals(0, data.triggerCount);
        task.execute();


                data.startCompleter.getFuture().get();

                // Trigger 3 times
                data.handler.trigger();
                Thread.sleep(10);
                data.handler.trigger();
                data.handler.trigger();
                data.handler.trigger();

                data.secondTimeCompleter.getFuture().get(10000);



        task.looper.quit();
        task.get();
    }
    */

    @Test
    public void testSingleOperationHandlerOnce() throws ExecutionException, InterruptedException, TimeoutException {
        DelayerHandlerBgTask task = new DelayerHandlerBgTask();
        Data data = task.data;
        assertEquals(0, data.triggerCount);

        task.execute();
        data.startCompleter.getFuture().get();

        // Trigger once
        data.handler.trigger();

        data.firstTimeCompleter.getFuture().get();
        // should timeout
        try {
            data.secondTimeCompleter.getFuture().get(1000);
        } catch (TimeoutException ignored) {
            // expected
        }

        task.looper.quit();
        task.get();

    }

    static class Data {
        SingleOperationHandler handler;
        int triggerCount;
        Completer.Void firstTimeCompleter = new Completer.Void();
        Completer.Void secondTimeCompleter = new Completer.Void();
        Completer.Void startCompleter = new Completer.Void();
    }

    static class DelayerHandlerBgTask extends BgTask {

        public Data data = new Data();
        SingleOperationHandler.Listener listener = new SingleOperationHandler.Listener() {
            @Override
            public void onHandle() {
                final int current = data.triggerCount++;
                // devLog("current " + current);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (current == 0) {
                    data.firstTimeCompleter.complete();
                } else if (current == 1) {
                    data.secondTimeCompleter.complete();
                } else {
                    fail("should not run more than twice");
                }
                // devLog("current done " + current);
                data.handler.done();

            }

        };
        Looper looper;

        @Override
        protected void doInBackground() throws Exception {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            looper = Looper.myLooper();

            data.handler = new SingleOperationHandler(listener);
            data.startCompleter.complete();
            Looper.loop();
        }
    }
}
