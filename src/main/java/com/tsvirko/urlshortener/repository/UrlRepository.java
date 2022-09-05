package com.tsvirko.urlshortener.repository;

import com.tsvirko.urlshortener.domain.entity.Url;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UrlRepository extends JpaRepository<Url, UUID> {
    Optional<Url> findByOriginal(String original);
    Optional<Url> findByLink(String link);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Url u SET u.count = u.count + 1 WHERE u.link =:link")
    void addCount(@Param("link") String link);

    @Query("SELECT u FROM Url u ORDER BY u.count DESC")
    List<Url> sortAllByCount();
}
