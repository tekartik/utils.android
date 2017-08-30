package com.tekartik.utils.utils.core;

import java.util.Collection;

/**
 * Created by alex on 10/04/17.
 */

public class CollectionUtils {

    static public boolean isEmpty(Collection<?> coll) {
        return (coll == null) || (coll.isEmpty());
    }

    static public int getSize(Collection<?> coll) {
        return (coll == null) ? 0 : coll.size();
    }

}
