package com.example.shoppingcart.cart.exception;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;

import ch.qos.logback.classic.Logger;


@ControllerAdvice
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	private String message;
    private Object[] arguments;
    
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    
    
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return new ResponseEntity<>("Invalid input: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle a EntityNotFoundException exception
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleGenericException(EntityNotFoundException ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    // Handle a InternalServerError exception
    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<String> handleGenericException(InternalServerError ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
 // Handle a InternalServerError exception
    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<String> handleGenericException(ClassCastException ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
 // Handle a InternalServerError exception
    @ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleGenericException(org.springframework.dao.EmptyResultDataAccessException ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

 // Handle a InternalServerError exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
   
}