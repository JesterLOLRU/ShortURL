package com.tsvirko.urlshortener.utils;

import java.security.SecureRandom;

public class UrlGenerator {
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom rnd = new SecureRandom();

    public static String generateURL() {
        StringBuilder sb = new StringBuilder();
        sb.append("/l/");
        for (int i = 0; i < 8; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
}
