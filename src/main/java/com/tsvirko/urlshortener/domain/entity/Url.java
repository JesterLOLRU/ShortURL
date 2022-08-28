package com.tsvirko.urlshortener.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "url")
public class Url extends BaseEntity{

    @Column(name = "original")
    private String original;

    @Column(name = "link")
    private String link;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "count")
    private Integer count;
}
