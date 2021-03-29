package com.marvel.controller.advice;

import com.marvel.exception.ErrorInfo;
import com.marvel.exception.MarvelException;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MarvelControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarvelException.class);

    @ExceptionHandler(MarvelException.class)
    public ResponseEntity<ErrorInfo> slotExceptionHandler(MarvelException marvelException) {
        LOGGER.error(marvelException.getMessage(), marvelException);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(marvelException.getMessage());
        return ResponseEntity.badRequest().body(errorInfo);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> ExceptionHandler(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage("Some error occured, please contact admin.");
        return ResponseEntity.badRequest().body(errorInfo);
    }

}
