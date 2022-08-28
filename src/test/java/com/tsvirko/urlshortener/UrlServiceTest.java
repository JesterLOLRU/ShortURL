package com.tsvirko.urlshortener;

import com.tsvirko.urlshortener.domain.dto.LinkDto;
import com.tsvirko.urlshortener.domain.entity.Url;
import com.tsvirko.urlshortener.repository.UrlRepository;
import com.tsvirko.urlshortener.service.impl.UrlServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UrlServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class UrlServiceTest {
    @MockBean
    private UrlRepository urlRepository;

    @Autowired
    private UrlServiceImpl urlServiceImpl;

    @Test
    void testShortURL() {
        Url url = new Url();
        url.setCount(3);
        url.setId(UUID.randomUUID());
        url.setLink("https://example.org/example");
        url.setOriginal("https://example.org/example");
        url.setRank(1);

        Url url1 = new Url();
        url1.setCount(3);
        url1.setId(UUID.randomUUID());
        url1.setLink("https://example.org/example");
        url1.setOriginal("https://example.org/example");
        url1.setRank(1);
        Optional<Url> ofResult = Optional.of(url1);
        when(this.urlRepository.saveAndFlush(any())).thenReturn(url);
        when(this.urlRepository.findByOriginal(any())).thenReturn(ofResult);
        LinkDto actualShortURLResult = this.urlServiceImpl.shortURL("https://example.org/example");
        assertEquals("https://example.org/example", actualShortURLResult.getLink());
        assertNull(actualShortURLResult.getOriginal());
        verify(this.urlRepository).saveAndFlush(any());
        verify(this.urlRepository, atLeast(1)).findByOriginal(any());
    }

    @Test
    void testGetUrlForRedirect() {
        Url url = new Url();
        url.setCount(3);
        url.setId(UUID.randomUUID());
        url.setLink("https://example.org/example");
        url.setOriginal("https://example.org/example");
        url.setRank(1);
        Optional<Url> ofResult = Optional.of(url);
        when(this.urlRepository.findByLink(any())).thenReturn(ofResult);
        ResponseEntity<Void> actualUrlForRedirect = this.urlServiceImpl.getUrlForRedirect("https://example.org/example");
        assertNull(actualUrlForRedirect.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualUrlForRedirect.getStatusCode());
        assertTrue(actualUrlForRedirect.getHeaders().isEmpty());
        verify(this.urlRepository).findByLink(any());
    }
}


