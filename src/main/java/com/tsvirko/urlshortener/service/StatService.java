package com.tsvirko.urlshortener.service;

import com.tsvirko.urlshortener.domain.dto.UrlDto;
import com.tsvirko.urlshortener.domain.entity.Url;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatService {

    /**
     * Method get stats of URL.
     * @param url shorted URL
     * @return Statistic of URL(rank in top of URLs, number of transitions).
     */
    UrlDto getStatOfUrl(String url);

    /**
     * Method get stats of all URLs in DB sorted by rank.
     * @param page Number of page
     * @param count Number of entry on page
     */
    List<Url> getAllStat(Integer page, Integer count);
}
