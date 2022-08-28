package com.tsvirko.urlshortener.task;

import com.tsvirko.urlshortener.domain.entity.Url;
import com.tsvirko.urlshortener.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class CheckRankStatusTask {

    @Autowired
    UrlRepository urlRepository;

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void checkRankStatus() {
        var links = urlRepository.findAllByCount();
        int i = 1;
        for (Url url : links) {
            url.setRank(i);
            urlRepository.saveAndFlush(url);
            i++;
        }
        log.info("Check URL rank status successful");
    }
}
