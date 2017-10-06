package com.tekartik.utils.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    // Compute the number to item remove from set1 to set2
    static public <T> Set<T> removed(Collection<T> set1, Collection<T> set2) {
        Set<T> set = new HashSet<>();
        if (set1 != null) {
            if (set2 == null) {
                return new HashSet<>(set1);
            } else {
                for (T item : set1) {
                    if (!set2.contains(item)) {
                        set.add(item);
                    }
                }
            }
        }
        return set;
    }

    // item from list1 added by list2
    static public <T> Set<T> added(Collection<T> list1, Collection<T> list2) {
        return removed(list2, list1);
    }
}
