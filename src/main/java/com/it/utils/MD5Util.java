package com.it.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private static final char[] DIGITS_UPPER =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }
    public  static String getMD5(String encoderString, String salt ,String code){
        try {
            byte[] bytes = MessageDigest.getInstance("MD5").digest((salt==null?encoderString:encoderString+salt).getBytes(code));
            return new String(encodeHex(bytes,DIGITS_UPPER));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getMD5(String encoderString){
        return getMD5(encoderString,null,"UTF-8");

    };
    public static String getMD5(String encodeString,String salt){
        return getMD5(encodeString,salt,"UTF-8");
    };
}
