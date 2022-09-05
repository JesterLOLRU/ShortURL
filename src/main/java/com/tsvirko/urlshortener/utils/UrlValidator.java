package com.tsvirko.urlshortener.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidator {

    public static boolean checkOriginalUrlValid(String url) {
        Pattern pattern = Pattern.compile("((?:(?:http?|ftp)[s]*:\\/\\/)?[a-z0-9-%\\/\\&=?\\.]+\\.[a-z]{2,4}\\/?([^\\s<>\\#%\"\\,\\{\\}\\\\|\\\\\\^\\[\\]`]+)?)");
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    public static boolean checkShortedUrlValid(String url) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{8}");
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
}
