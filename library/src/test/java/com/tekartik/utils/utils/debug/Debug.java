package com.tekartik.utils.utils.debug;

import java.util.logging.Logger;

public class Debug {
    /**
     * commit with this DLOG = Constants.DLOG_DEFAULT;
     */
    static public boolean DLOG_DEFAULT = false;

    /**
     * deprecated to prevent commiting
     * <p>
     * DLOG = Constants.DLOG_DEBUG;
     */
    @Deprecated
    static public boolean DLOG_DEBUG = true;

    @Deprecated
    static public boolean CACHE_OFF_DEBUG = false;

    static public long NO_DELAY = 0;

    static public boolean NO = false;

    static public Logger log = Logger.getLogger("Debug");

    // For debug only
    @Deprecated
    static public void devLog(String msg) {
        log.warning(msg);
    }

    // For debug only
    @Deprecated
    static public void devLog(String tag, String msg) {
        Logger.getLogger(tag).warning(tag + ": " + msg);
    }
}
