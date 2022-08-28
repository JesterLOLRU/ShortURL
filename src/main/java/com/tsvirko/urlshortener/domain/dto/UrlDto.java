package com.tsvirko.urlshortener.domain.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlDto {
    private String original;
    private String link;
    private Integer rank;
    private Integer count;
}
