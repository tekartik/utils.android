package com.tekartik.utils.security;

import com.tekartik.utils.hex.HexUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alex on 14/09/17.
 */

public class HashUtils {
    static public String md5(String s) {
        if (s == null) {
            return null;
        }
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            return HexUtils.byteArrayToHexString(messageDigest).toLowerCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    static public String sha1(String s) {
        if (s == null) {
            return null;
        }
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            return HexUtils.byteArrayToHexString(messageDigest).toLowerCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
