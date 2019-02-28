package com.tekartik.android.utils;

import android.os.ParcelFileDescriptor;
import android.util.Log;

import com.tekartik.utils.stream.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ParcelFileDescriptorUtils {

    static public String TAG = "PfdUtils";

    // This causes broken pipe in a 2018-05-02 experiment
    static public ParcelFileDescriptor fromStreamThread(InputStream inputStream)
            throws IOException {
        ParcelFileDescriptor[] pipe = null;

        pipe = ParcelFileDescriptor.createPipe();

        new TransferThread(inputStream,
                new ParcelFileDescriptor.AutoCloseOutputStream(pipe[1])).start();

        return (pipe[0]);
    }

    static public ParcelFileDescriptor fromStream(InputStream inputStream)
            throws IOException {
        ParcelFileDescriptor[] pipe = null;

        pipe = ParcelFileDescriptor.createPipe();

        OutputStream outputStream = new ParcelFileDescriptor.AutoCloseOutputStream(pipe[1]);
        try {
            StreamUtils.copyStream(inputStream, outputStream);
            outputStream.flush();
        } finally {
            inputStream.close();
            outputStream.close();
        }

        return (pipe[0]);
    }

    static class TransferThread extends Thread {
        InputStream in;
        OutputStream out;

        TransferThread(InputStream in, OutputStream out) {
            this.in = in;
            this.out = out;
        }

        @Override
        public void run() {
            byte[] buf = new byte[8192];
            int len;

            try {
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                in.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                Log.e(TAG,
                        "Exception transferring file", e);
            }
        }
    }
}
