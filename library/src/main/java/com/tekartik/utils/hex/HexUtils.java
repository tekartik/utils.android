package com.tekartik.utils.hex;

import java.io.ByteArrayOutputStream;

/**
 * Created by alex on 30/08/17.
 */

public class HexUtils {

    public static byte[] hexStringToByteArray(String s) {
        if (s == null) {
            return null;
        }
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

    public static String byteArrayToHexString(byte[] data) {
        if (data == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            sb.append(hexCode[(b >> 4) & 0xF]);
            sb.append(hexCode[(b & 0xF)]);
        }
        return sb.toString();
    }

    static public byte[] parse(String hexString) {
        if (hexString == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(hexString.length() / 2);
        int firstNibble = -1;

        for (char c : hexString.toCharArray()) {
            if (firstNibble == -1) {
                firstNibble = Character.digit(c, 16);
            } else {
                int secondNibble = Character.digit(c, 16);
                if (secondNibble != -1) {
                    out.write(firstNibble * 16 + secondNibble);
                    firstNibble = -1;
                } else {
                    firstNibble = -1;
                }
            }
        }
        return out.toByteArray();
    }
}
