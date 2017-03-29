package com.app.parking;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by gatomulesei on 3/28/2017.
 */
public class Md5Encryption {

    public static String encrypt(String password) {
        MessageDigest messageDigest;
        StringBuilder sb = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] bytes = messageDigest.digest();
            sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++) {
                sb.append(bytes[i]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
