package com.michal.examples.spring.security.controller;

import com.michal.examples.spring.security.exception.CardNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({CardNotFoundException.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ResponseBody
    public ResponseEntity<Void> handleException(CardNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}
