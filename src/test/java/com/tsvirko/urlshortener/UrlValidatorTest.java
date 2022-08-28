package com.tsvirko.urlshortener;

import com.tsvirko.urlshortener.utils.UrlValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrlValidatorTest {

    @Test
    public void validOriginalUrl() {
        assertTrue(UrlValidator.checkOriginalUrlValid("https://example.org/example"));
    }

    @Test
    public void invalidOriginalUrl() {
        assertFalse(UrlValidator.checkOriginalUrlValid("https://exampleorg/example"));
    }

    @Test
    public void validLinkUrl() {
        assertTrue(UrlValidator.checkShortedUrlValid("blabla12"));
    }

    @Test
    public void invalidLinkUrl() {
        assertFalse(UrlValidator.checkShortedUrlValid("blablabla"));
    }
}
