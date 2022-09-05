package com.tsvirko.urlshortener;

import com.tsvirko.urlshortener.domain.dto.UrlDto;
import com.tsvirko.urlshortener.domain.entity.Url;
import com.tsvirko.urlshortener.repository.UrlRepository;
import com.tsvirko.urlshortener.service.impl.StatServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StatServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class StatServiceTest {

    @Autowired
    private StatServiceImpl statServiceImpl;

    @MockBean
    private UrlRepository urlRepository;

    @Test
    void testGetStatOfUrl() {
        Url url = new Url();
        url.setCount(3);
        url.setId(UUID.randomUUID());
        url.setLink("https://example.org/example");
        url.setOriginal("https://example.org/example");
        url.setRank(1);
        Optional<Url> ofResult = Optional.of(url);
        when(this.urlRepository.findByLink(any())).thenReturn(ofResult);
        UrlDto actualStatOfUrl = this.statServiceImpl.getStatOfUrl("https://example.org/example");
        assertEquals(3, actualStatOfUrl.getCount().intValue());
        assertEquals(1, actualStatOfUrl.getRank().intValue());
        assertEquals("https://example.org/example", actualStatOfUrl.getOriginal());
        assertEquals("https://example.org/example", actualStatOfUrl.getLink());
        verify(this.urlRepository).findByLink(any());
    }

    @Test
    void testGetAllStat() {
        when(this.urlRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.statServiceImpl.getAllStat(1, 3).isEmpty());
        verify(this.urlRepository).findAll((org.springframework.data.domain.Pageable) any());
    }
}
