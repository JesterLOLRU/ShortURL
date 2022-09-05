package com.tsvirko.urlshortener.task;

import com.tsvirko.urlshortener.domain.entity.Url;
import com.tsvirko.urlshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;

@Component
@Slf4j
@RequiredArgsConstructor
public class CheckRankStatusTask {

    private final UrlRepository urlRepository;

    private final ScheduledAnnotationBeanPostProcessor postProcessor;

    @Scheduled(fixedRateString = "${fixedDelay.milliseconds}")
    @Transactional
    public void checkRankStatus() {
        var links = urlRepository.sortAllByCount();
        int i = 1;
        for (Url url : links) {
            url.setRank(i);
            i++;
        }
        urlRepository.saveAll(links);
        log.info("Check URL rank status successful");
    }

    @PreDestroy
    public void stopThis() {
     postProcessor.postProcessBeforeDestruction(this, "");
    }
}
