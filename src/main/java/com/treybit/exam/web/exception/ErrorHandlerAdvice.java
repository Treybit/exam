package com.treybit.exam.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerAdvice {

    @ExceptionHandler(PruefungNichtGefundenException.class)
    public String handleException(PruefungNichtGefundenException exception) {
        return "404";
    }
}
