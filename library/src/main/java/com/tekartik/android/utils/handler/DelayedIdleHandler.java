package com.tekartik.android.utils.handler;

import android.os.Handler;

/**
 * Will trigger onHandler \<delay\> ms after last trigger
 */
public class DelayedIdleHandler implements DelayedHandler {
    private final Handler handler = new Handler();
    private final long delay;
    private final DelayedHandlerListener listener;

    final Runnable callback = new Runnable() {
        @Override
        public void run() {
            listener.onHandle();
        }
    };

    public DelayedIdleHandler(long delay, DelayedHandlerListener listener) {
        this.delay = delay;
        this.listener = listener;
    }


    public void trigger() {
        handler.removeCallbacks(callback);
        handler.postDelayed(callback, delay);
    }
}
