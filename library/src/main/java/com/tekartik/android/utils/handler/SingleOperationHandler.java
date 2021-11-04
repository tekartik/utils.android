package com.tekartik.android.utils.handler;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

/**
 * Created by alex on 17/5/18.
 */

// Trigger onHandle right away if not running
// Will trigger when done (need to call done)
// This ensure that onHandle is always call once (and only once) after a trigger called
// always called int the same thread
public class SingleOperationHandler implements DelayedHandler {

    @NonNull
    private final Listener listener;
    private final Handler handler = new Handler(Looper.myLooper());
    private long handleRevision = 0;
    private long triggerRevision = 0;
    private boolean pending = false;

    public SingleOperationHandler(@NonNull Listener listener) {
        this.listener = listener;
    }

    // only if it was triggered after the last time it was handled
    private void check() {
        // devLog("pending " + pending + " trigger " + triggerRevision + " handle " + handleRevision);
        if ((!pending) && (triggerRevision > handleRevision)) {
            handle();
        }
    }

    // run
    private void handle() {
        pending = true;
        handleRevision = triggerRevision;
        // devLog("handle " + handleRevision);
        handler.post(new Runnable() {
            @Override
            public void run() {
                // devLog("handle (post)" + handleRevision);
                listener.onHandle();
            }
        });
    }

    // thread safe
    public void trigger() {
        ++triggerRevision;
        check();
    }

    // thread safe, to call when done
    public void done() {
        pending = false;
        check();
    }

    public interface Listener {
        void onHandle();
    }
}
