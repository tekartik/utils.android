package com.tekartik.android.utils.handler;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

/**
 * Created by alex on 28/09/17.
 */

// Trigger onHandle right away and wait at least [delay] before triggering the next
// always called int the same thread
public class DelayedBusyHandler implements DelayedHandler {
    private final long delay;
    @NonNull
    private final DelayedHandlerListener listener;
    private final Handler handler = new Handler(Looper.myLooper());
    private boolean pending = false;
    private boolean triggered = false;

    public DelayedBusyHandler(long delay, @NonNull DelayedHandlerListener listener) {
        this.delay = delay;
        this.listener = listener;
    }

    private void check() {
        if (!pending) {
            pending = true;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onHandle();
                }
            });

            triggered = false;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pending = false;
                    if (triggered) {
                        check();
                    }
                }
            }, delay);

        }
    }

    public void trigger() {
        triggered = true;
        check();
    }

    public interface Listener {
        void onHandle();
    }
}
