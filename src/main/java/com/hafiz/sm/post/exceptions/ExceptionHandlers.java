package com.hafiz.sm.post.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlers {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExceptionHolders.InvalidRequestException.class)
    public String handleResourceNotFoundException(final ExceptionHolders.InvalidRequestException ex) {
        return ex.getMessage();
    }

    //Give proper http status
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler(ConnectException.class)
    public String handleConnectionException(final ConnectException ex) {
        return ex.getMessage();
    }

}