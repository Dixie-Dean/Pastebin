package com.dixie.pastebin.exception.advice;

import com.dixie.pastebin.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> userAlreadyExistExceptionHandler(UserAlreadyExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
