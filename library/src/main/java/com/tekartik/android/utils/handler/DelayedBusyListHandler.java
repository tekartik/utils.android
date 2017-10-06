package com.tekartik.android.utils.handler;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 28/09/17.
 */

public class DelayedBusyListHandler<V> implements DelayedHandler {
    final DelayedHandler delayedHandler;
    final private List<V> list = new ArrayList<>();

    public interface Listener<V> {
        void onHandle(List<V> list);
    }

    public DelayedBusyListHandler(long delay, @NonNull final Listener<V> listener) {
        delayedHandler = new DelayedBusyHandler(delay, new DelayedHandlerListener() {
            @Override
            public void onHandle() {
                List<V> handleList;

                synchronized (list) {
                    handleList = new ArrayList<>(list);
                }
                list.clear();
                listener.onHandle(handleList);
            }
        });

    }

    public void add(V value) {
        synchronized (list) {
            list.add(value);
        }
        trigger();
    }

    public void trigger() {
        delayedHandler.trigger();
    }
}
