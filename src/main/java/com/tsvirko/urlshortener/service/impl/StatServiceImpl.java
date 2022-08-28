package com.tsvirko.urlshortener.service.impl;

import com.tsvirko.urlshortener.domain.dto.UrlDto;
import com.tsvirko.urlshortener.domain.entity.Url;
import com.tsvirko.urlshortener.repository.UrlRepository;
import com.tsvirko.urlshortener.service.StatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StatServiceImpl implements StatService {

    @Autowired
    private UrlRepository urlRepository;


    public UrlDto getStatOfUrl(String url) {
        var urlStat = urlRepository.findByLink(url);
        if (urlStat.isEmpty()) {
            log.warn("URL {} stat not found", url);
            return UrlDto.builder()
                    .original("Not found")
                    .build();
        }
        return UrlDto.builder()
                .link(url)
                .original(urlStat.get().getOriginal())
                .count(urlStat.get().getCount())
                .rank(urlStat.get().getRank())
                .build();
    }

    public List<Url> getAllStat(Integer page, Integer count) {
        Pageable pageOfUrlStat = PageRequest.of(page,count, Sort.by("rank"));
        var res = urlRepository.findAll(pageOfUrlStat);
        log.info("All statistics were successfully uploaded");
        return res.getContent();
    }
}
