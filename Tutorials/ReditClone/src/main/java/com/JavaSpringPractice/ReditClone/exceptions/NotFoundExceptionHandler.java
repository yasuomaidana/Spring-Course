package com.JavaSpringPractice.ReditClone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class NotFoundExceptionHandler {
    @ExceptionHandler(value = {PostNotFoundException.class,SubRedditNotFoundException.class})
    public ResponseEntity<NotFoundException> notFoundHandler(RuntimeException e){
        NotFoundException notFoundException = new NotFoundException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now());
        return status(HttpStatus.NOT_FOUND).body(notFoundException);
    }
    @ExceptionHandler(value = {SpringRedditException.class})
    public ResponseEntity<NotFoundException> redditHandler(RuntimeException e){
        NotFoundException notFoundException = new NotFoundException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now());
        return status(HttpStatus.BAD_REQUEST).body(notFoundException);
    }
}
