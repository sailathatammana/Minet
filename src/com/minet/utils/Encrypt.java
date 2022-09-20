package com.minet.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

    public static String encryptPassword(String input) {
        String hashValue = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashByte = md.digest(input.getBytes());
            BigInteger bigValue = new BigInteger(1, hashByte);
            hashValue = bigValue.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return hashValue;
    }
}
