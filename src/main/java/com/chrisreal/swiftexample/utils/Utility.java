package com.chrisreal.swiftexample.utils;

import java.security.SecureRandom;
import java.util.Random;

public class Utility {
    public static String generatedRandomCharacters(int length) {
        String alphaNumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new SecureRandom();
        StringBuilder result = new StringBuilder(length);

        for(int i = 0; i < length; i++) {
            result.append(alphaNumeric.charAt(random.nextInt(alphaNumeric.length())));
        }

        return new String(result);
    }
}
