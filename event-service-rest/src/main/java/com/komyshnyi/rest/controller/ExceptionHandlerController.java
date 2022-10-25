package com.komyshnyi.rest.controller;

import com.komyshnyi.impl.exception.EntityRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestControllerAdvice
public class ExceptionHandlerController {

    Logger logger = Logger.getLogger(ExceptionHandlerController.class.getName());
    private static final String MESSAGE = "message";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        logger.log(Level.SEVERE, ex.getMessage(), ex);
        return new ResponseEntity<>(Map.of(MESSAGE, "Unexpected error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleEntityNotFountException(EntityNotFoundException ex) {
        logger.log(Level.WARNING, ex.getMessage(), ex);
        return new ResponseEntity<>(Map.of(MESSAGE, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityRequestException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Map<String, String>> handleEntityRequestException(EntityRequestException ex) {
        logger.log(Level.WARNING, ex.getMessage(), ex);
        return new ResponseEntity<>(Map.of(MESSAGE, ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.log(Level.WARNING, ex.getMessage(), ex);
        return new ResponseEntity<>(Map.of(MESSAGE, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}