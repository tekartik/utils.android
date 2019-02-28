package com.tekartik.android.utils;

import android.os.Build;

public class DeviceUtils {
    static private Boolean isEmulator;

    static public boolean isEmulator() {
        if (isEmulator == null) {
            isEmulator = com.tekartik.utils.device.DeviceUtils.isEmulator(Build.PRODUCT);
        }
        return isEmulator;
    }
}