package com.tekartik.android.utils.handler;

import android.os.Handler;
import androidx.annotation.NonNull;

import java.util.Date;

/**
 * Created by alex on 17/5/18.
 */

// Trigger onHandle right away if not running
// Will trigger when done (need to call done)
// This ensure that onHandle is always call once (and only once) after a trigger called
// always called int the same thread
public class SingleOperationHandler implements DelayedHandler {

    // set when onHandle is called
    // clear on done()
    private Date lastTriggeredDate;
    private Date lastHandledDate;

    @NonNull
    private final Listener listener;
    private boolean pending = false;
    private final Handler handler = new Handler();

    public interface Listener {
        void onHandle();
    }

    public SingleOperationHandler(@NonNull Listener listener) {
        this.listener = listener;
    }

    // only if it was triggered after the last time it was handled
    private void check() {
        if (!pending && (lastTriggeredDate != null &&
                (lastHandledDate == null || lastHandledDate.before(lastTriggeredDate)))) {
            handle();
        }
    }

    // run
    private void handle() {
        pending = true;
        lastHandledDate = new Date();
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onHandle();
            }
        });
    }

    // thread safe
    public void trigger() {
        lastTriggeredDate = new Date();
        check();
    }

    // thread safe, to call when done
    public void done() {
        pending = false;
        check();
    }
}
