package com.tsvirko.urlshortener.controller;

import com.tsvirko.urlshortener.domain.dto.ExceptionDto;
import com.tsvirko.urlshortener.exception.RestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestControllerAdviceExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ExceptionDto> handleException(RestException ex) {
        log.error("Caught rest exception {}", ex.toString());

        var exceptionDto = ExceptionDto.builder()
                .message(ex.getMsg())
                .httpStatus(ex.getHttpStatus())
                .build()
                ;

        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(exceptionDto)
                ;
    }
}