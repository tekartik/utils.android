package com.tekartik.utils.core;

import java.util.List;

/**
 * Created by alex on 02/10/17.
 */

public class ListUtils {

    // Safe get from a list
    static public <T> T get(List<T> list, int index) {
        if (list != null && index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }

    static public <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }
}
