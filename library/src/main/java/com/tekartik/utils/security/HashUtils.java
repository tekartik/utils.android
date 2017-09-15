package com.tekartik.utils.security;

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
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
