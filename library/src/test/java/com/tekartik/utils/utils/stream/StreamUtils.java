package com.tekartik.utils.utils.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by alex on 30/09/16.
 */

public class StreamUtils {


    public static void copyStream(InputStream is, OutputStream os) throws IOException {
        final int buffer_size = 0x10000;

        byte[] bytes = new byte[buffer_size];
        for (; ; ) {
            int count = is.read(bytes, 0, buffer_size);
            if (count == -1)
                break;
            os.write(bytes, 0, count);
        }

    }
}
