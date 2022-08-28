package com.tsvirko.urlshortener.controller;

import com.tsvirko.urlshortener.domain.dto.LinkDto;
import com.tsvirko.urlshortener.domain.dto.UrlDto;
import com.tsvirko.urlshortener.domain.entity.Url;
import com.tsvirko.urlshortener.service.StatService;
import com.tsvirko.urlshortener.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/url")
@RestController
@Tag(name = "URL Shortener Controller", description = "Controller for URL Shortener")
public class ShortenerController {

    @Autowired
    private UrlService shortenerUrlService;

    @Autowired
    private StatService statService;

    @PostMapping(path = "/generate")
    @Operation(summary = "Short URL")
    public LinkDto shortUrl(@RequestBody LinkDto url) {
        return shortenerUrlService.shortURL(url.getOriginal());
    }

    @GetMapping(path = "/l/{url}")
    @Operation(summary = "Redirect by short URL")
    public ResponseEntity<Void> redirect(@PathVariable String url) {
        return shortenerUrlService.getUrlForRedirect(url);
    }

    @GetMapping(path = "/stats/{url}")
    @Operation(summary = "Get stat of URL")
    public UrlDto getStat(@PathVariable String url) {
        return statService.getStatOfUrl(url);
    }

    @GetMapping(path = "/stats/")
    @Operation(summary = "Get stat of all URLs")
    public List<Url> getAllStat(@RequestParam Integer page,
                                @RequestParam Integer count) {
        return statService.getAllStat(page, count);
    }

}
