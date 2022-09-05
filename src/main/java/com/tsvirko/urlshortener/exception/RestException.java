package com.tsvirko.urlshortener.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestException extends RuntimeException {
    private String msg;
    private HttpStatus httpStatus;

    public RestException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
}
