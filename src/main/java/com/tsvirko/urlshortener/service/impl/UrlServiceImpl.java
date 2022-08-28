package com.tsvirko.urlshortener.service.impl;

import com.tsvirko.urlshortener.domain.dto.LinkDto;
import com.tsvirko.urlshortener.domain.entity.Url;
import com.tsvirko.urlshortener.repository.UrlRepository;
import com.tsvirko.urlshortener.service.UrlService;
import com.tsvirko.urlshortener.utils.UrlGenerator;
import com.tsvirko.urlshortener.utils.UrlValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

@Service
@Slf4j
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public LinkDto shortURL(String url) {
        String processedUrl;
        if (UrlValidator.checkOriginalUrlValid(url)) {
            processedUrl = UrlGenerator.generateURL();
            if (urlRepository.findByOriginal(url).isEmpty()) {
                Url urlObject = new Url();
                urlObject.setOriginal(url);
                urlObject.setLink(processedUrl);
                urlObject.setCount(0);
                urlObject.setRank(0);
                urlRepository.saveAndFlush(urlObject);
                log.info("Successfully added url {} to database wtith short link {}", url,processedUrl);
            } else {
                var urlObjectEntity = urlRepository.findByOriginal(url);
                var urlObject = urlObjectEntity.get();
                processedUrl = urlObject.getLink();
                urlRepository.saveAndFlush(urlObject);
            }
        } else {
            log.warn("URL {} is invalid", url);
            processedUrl = "URL is invalid";
        }
        return LinkDto.builder()
                .link(processedUrl)
                .build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> getUrlForRedirect(String url) {
        var urlEntity = urlRepository.findByLink(url);
        if (!UrlValidator.checkShortedUrlValid(url) || urlEntity.isEmpty()) {
            log.warn("URL {} not found", url);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        } else {
            urlRepository.addCount(url);
            log.info("URL founded successfully");
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(urlEntity.get().getOriginal()))
                    .build();
        }
    }
}
