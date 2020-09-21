package com.kboxglobal.naydogdu.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ViewNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ViewNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String viewNotFoundHandler(ViewNotFoundException ex) {
        return ex.getMessage();
    }
}
