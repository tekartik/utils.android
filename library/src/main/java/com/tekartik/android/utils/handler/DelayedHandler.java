package com.tekartik.android.utils.handler;

/**
 * Created by alex on 28/09/17.
 */

// Trigger onHandle right away and wait at least [delay] before triggering the next
// always called int the same thread
public interface DelayedHandler {
    void trigger();
}
