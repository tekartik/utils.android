package com.tekartik.android.utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by alex on 02/10/16.
 */

public class FileUtils {

    /**
     * Get a usable cache directory (external if available, internal otherwise).
     *
     * @param context    The context to use
     * @param uniqueName A unique directory name to append to the cache dir
     * @return The cache dir
     */
    public static File getBestCacheDir(Context context, String uniqueName) {
        File dir = getExternalCacheDir(context, uniqueName);
        if (dir == null) {
            dir = getCacheDir(context, uniqueName);
        }
        return dir;
    }

    public static File getCacheDir(Context context, String uniqueName) {
        return getSubDir(getCacheDir(context), uniqueName);
    }

    public static File getExternalCacheDir(Context context, String uniqueName) {
        return getSubDir(getExternalCacheDir(context), uniqueName);
    }

    public static File getSubDir(File dir, String uniqueName) {
        File sub = new File(dir, uniqueName);
        if (!sub.exists()) {
            try {
                sub.mkdirs();
            } catch (Exception e) {
                try {
                    sub.delete();
                    sub.mkdirs();
                } catch (Exception e2) {
                    return null;
                }
            }
        }
        if (!sub.exists()) {
            return null;
        }
        return sub;
    }

    private static File getExternalCacheDir(Context context) {
        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
        // otherwise use internal cache dir
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return context.getExternalCacheDir();
        }
        return null;
    }

    public static File getCacheDir(Context context) {
        return context.getCacheDir();
    }

    public static boolean write(File file, String content) {

        OutputStream os = null;
        try {
            file.createNewFile();


            os = new FileOutputStream(file);
            os.write(content.getBytes());
            os.close();
            os = null;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String convertStreamToString(InputStream is) throws IOException {
        // http://www.java2s.com/Code/Java/File-Input-Output/ConvertInputStreamtoString.htm
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        Boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                sb.append(line);
                firstLine = false;
            } else {
                sb.append("\n").append(line);
            }
        }
        reader.close();
        return sb.toString();
    }

    public static String readString(File file) throws IOException {
        FileInputStream fin = new FileInputStream(file);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }
}
