package com.tekartik.utils.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by alex on 10/04/17.
 */

public class CollectionUtils {

    static public boolean isEmpty(Collection<?> collection) {
        return (collection == null) || (collection.isEmpty());
    }

    static public int getSize(Collection<?> collection) {
        return (collection == null) ? 0 : collection.size();
    }

    public static <T> List<T> copy(Collection<T> collection) {
        if (collection == null) {
            return null;
        }
        return new ArrayList<>(collection);
    }
}
