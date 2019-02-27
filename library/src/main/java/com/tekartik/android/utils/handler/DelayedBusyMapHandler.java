package com.tekartik.android.utils.handler;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 28/09/17.
 */

public class DelayedBusyMapHandler<K, V> implements DelayedHandler {
    final DelayedHandler delayedHandler;
    final private Map<K, V> map = new HashMap<>();

    public interface Listener<K, V> {
        void onHandle(Map<K, V> map);
    }

    public DelayedBusyMapHandler(long delay, @NonNull final Listener<K, V> listener) {
        delayedHandler = new DelayedBusyHandler(delay, new DelayedHandlerListener() {
            @Override
            public void onHandle() {
                Map<K, V> handleMap = new HashMap<>();
                handleMap.putAll(map);
                map.clear();
                listener.onHandle(handleMap);
            }
        });

    }

    public void put(K key, V value) {
        map.put(key, value);
        trigger();
    }

    public void trigger() {
        delayedHandler.trigger();
    }
}
