package com.tsvirko.urlshortener.service;

import com.tsvirko.urlshortener.domain.dto.LinkDto;
import org.springframework.http.ResponseEntity;

public interface UrlService {
    /**
     * Method short the url
     * @param url Url for shortener
     * @return shortened url
     */
    LinkDto shortURL(String url);

    /**
     * Method get base url from DB for redirect
     * @param url Shortened url
     * @return redirect to base url
     */
    ResponseEntity<Void> getUrlForRedirect(String url);
}
