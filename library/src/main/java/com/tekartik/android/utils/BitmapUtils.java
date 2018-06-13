package com.tekartik.android.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by alex on 03/10/16.
 */

public class BitmapUtils {
    static public Bitmap decodeFromFile(int targetW, int targetH, String imagePath) {

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        //inJustDecodeBounds = true <-- will not load the bitmap into memory
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
        return (bitmap);
    }

    static public Bitmap getResizedBitmap(int targetW, int targetH, String imagePath) {
        return decodeFromFile(targetW, targetH, imagePath);
    }
}
