package com.tekartik.android.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.CallSuper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Use this instead of AsyncTask
 *
 * @author alex
 */
public abstract class ExecutorTask {

    private final ExecutorService executorService;
    private Exception exception;
    private String name;

    private static ExecutorService defaultExecutorService;

    private static ExecutorService getDefaultExecutor() {
        if (defaultExecutorService == null) {
            synchronized (ExecutorTask.class) {
                defaultExecutorService = Executors.newSingleThreadExecutor();
            }
        }
        return defaultExecutorService;
    }
    public ExecutorTask(ExecutorService executors) {
        this.executorService = executors;
    }

    public ExecutorTask() {
        this(getDefaultExecutor());
    }

    public ExecutorTask(String name) {
        this();
        this.name = name;
    }

    public ExecutorTask(ExecutorService executors, String name) {
        this(executors);
    }

    /**
     * To override
     */
    protected abstract void doInBackground() throws Exception;

    /**
     * To (optionnally) override
     */
    @CallSuper
    protected void onPostExecute() {
    }

    /**
     * Start the task in the background
     */
    public ExecutorTask execute() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Thread thread = Thread.currentThread();

                // Set the name
                String oldName = null;
                if (name != null) {
                    oldName = thread.getName();
                    thread.setName(name);
                }
                Log.d("ExecutorTask", "Running");
                try {

                    ExecutorTask.this.doInBackground();
                } catch (Exception e) {
                    // In debug we throw the exception
                    // Catch it if you need
                    ExecutorTask.this.exception = e;
                    e.printStackTrace();
                }

                // restore the name
                if (name != null) {
                    thread.setName(oldName);
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        onPostExecute();
                    }
                });
            }
        });
        return this;
    }

    /**
     * Get the exception thrown during background operation
     * <p>
     * Can be called during onPostExecute
     *
     * @return
     */
    protected Exception getException() {
        return exception;
    }


}
