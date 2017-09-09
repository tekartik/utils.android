package com.tekartik.android.utils;

import android.content.Context;

import com.tekartik.utils.stream.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by alex on 30/08/16.
 * <p>
 * asset utilities
 */
public class AssetUtils {

    /*
    static public String readDemoJsonAsset(Context context, String name) {
        return readAsset(context, "demo/" + name);
    }

    static public String readAsset(Context context, String name) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(name), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                sb.append(mLine);
                sb.append("\n");
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return sb.toString();
    }
    */

    static public byte[] readAsset(Context context, String path) {
        try {
            InputStream inputStream = context.getAssets().open(path);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                StreamUtils.copyStream(inputStream, outputStream);
            } catch (IOException e) {
                inputStream.close();
                outputStream.close();
            }
            return outputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
