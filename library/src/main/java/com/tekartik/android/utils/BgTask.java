package com.tekartik.android.utils;

import android.os.AsyncTask;
import android.support.annotation.CallSuper;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Use this instead of AsyncTask
 *
 * @author alex
 */
public abstract class BgTask {

    private String name;
    AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {

        @Override
        protected Void doInBackground(Void... params) {
            Thread thread = Thread.currentThread();

            // Set the name
            String oldName = null;
            if (name != null) {
                oldName = thread.getName();
                thread.setName(name);
            }
            try {

                BgTask.this.doInBackground();
            } catch (Exception e) {
                // In debug we throw the exception
                // Catch it if you need
                if (BuildConfig.DEBUG) {
                    throw new RuntimeException(e);
                }
                e.printStackTrace();
            }

            // restore the name
            if (name != null) {
                thread.setName(oldName);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                BgTask.this.onPostExecute();
            } catch (Exception e) {
                // In debug we throw the exception
                // Catch it if you need
                if (BuildConfig.DEBUG) {
                    throw new RuntimeException(e);
                }
                e.printStackTrace();
            }
        }

        ;

    };

    public BgTask() {
    }

    public BgTask(String name) {
        this.name = name;
    }

    /**
     * To override
     */
    protected abstract void doInBackground() throws Exception;

    /**
     * To (optionnally) override
     */
    protected void onPostExecute() {
    }

    /**
     * Start the task in the background
     */
    public BgTask execute() {
        asyncTask.execute((Void) null);
        return this;
    }

    public void get() throws ExecutionException, InterruptedException {
        asyncTask.get();
    }

    public void get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
        asyncTask.get(timeout, unit);
    }

    public void get(long msTimeout) throws InterruptedException, ExecutionException, TimeoutException {
        get(msTimeout, TimeUnit.MILLISECONDS);
    }

}
