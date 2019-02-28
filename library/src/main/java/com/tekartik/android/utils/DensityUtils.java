package com.tekartik.android.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import static android.util.DisplayMetrics.DENSITY_DEFAULT;

public class DensityUtils {

    static DisplayMetrics metrics;

    static DisplayMetrics getDisplayMetrics(Context context) {
        if (metrics == null) {
            Resources resources = context.getResources();
            metrics = resources.getDisplayMetrics();
        }
        return metrics;
    }

    /**
     * This method convets dp unit to equivalent device specific value in
     * pixels.
     *
     * @param dp      A value in dp(Device independent pixels) unit. Which we need
     *                to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent Pixels equivalent to dp according to
     * device
     */
    public static float convertDpToPixel(float dp, Context context) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        float px = dp * metrics.densityDpi / DENSITY_DEFAULT;
        return px;
    }

    /**
     * This method converts device specific pixels to device independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent db equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        float dp = px * DENSITY_DEFAULT / metrics.densityDpi;
        return dp;

    }
}
