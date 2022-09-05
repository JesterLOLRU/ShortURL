package com.tsvirko.urlshortener.controller;

import com.tsvirko.urlshortener.domain.dto.LinkDto;
import com.tsvirko.urlshortener.domain.dto.UrlDto;
import com.tsvirko.urlshortener.domain.entity.Url;
import com.tsvirko.urlshortener.service.StatService;
import com.tsvirko.urlshortener.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/url")
@RestController
@Validated
@Tag(name = "URL Shortener Controller", description = "Controller for URL Shortener")
public class ShortenerController {

    private final UrlService shortenerUrlService;

    private final StatService statService;

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
    public List<Url> getAllStat(@NotNull @Min(0) @RequestParam Integer page,
                                @NotNull @Min(1) @RequestParam Integer count) {
        return statService.getAllStat(page, count);
    }

}
