package com.epam.ms.controller.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorData> constraintViolationErrorHandler(ConstraintViolationException e) {
        ErrorData errorData = new ErrorData(e.getMessage(), e);
        logger.error("Validation for the bean failed", e);
        return ResponseEntity.badRequest().body(errorData);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorData> baseErrorHandler(Exception e) {
        ErrorData errorData = new ErrorData(e.getMessage(), e);
        logger.error("Unexpected error", e);
        return ResponseEntity.status(500).body(errorData);
    }
}
