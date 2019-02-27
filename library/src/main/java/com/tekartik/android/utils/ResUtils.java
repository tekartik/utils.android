package com.tekartik.android.utils;

import android.content.res.Resources;
import androidx.annotation.RawRes;

import com.tekartik.utils.stream.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by alex on 30/03/17.
 *
 * Good for loading json for example
 */
public class ResUtils {

    static public String loadRawStringId(Resources res, @RawRes int resId) {
        InputStream inputStream = res.openRawResource(resId);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            StreamUtils.copyStream(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                inputStream.close();
            } catch (Exception ignore) {
            }
            try {
                outputStream.close();
            } catch (Exception ignore) {
            }
        }
        String read = null;
        try {
            read = new String(outputStream.toByteArray(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return read;
    }
}


