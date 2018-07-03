package com.falcon.demo.configs;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ControllerAdvice(basePackages = "com.falcon.demo")
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(SERVICE_UNAVAILABLE)
    public String handleException() {
        return "We could not process your message, please try again.";
    }

    @ExceptionHandler(value
                              = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "We could not process your message, please try again.";
        return handleExceptionInternal(ex, bodyOfResponse,
                                       new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

}
