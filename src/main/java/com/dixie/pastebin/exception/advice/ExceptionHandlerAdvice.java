package com.dixie.pastebin.exception.advice;

import com.dixie.pastebin.dto.authentication.ExceptionResponse;
import com.dixie.pastebin.exception.UserAlreadyExistException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> userAlreadyExistExceptionHandler(UserAlreadyExistException exception) {
        ExceptionResponse response = new ExceptionResponse(
                exception.getClass().toString(),
                "User with such email already registered!",
                exception.getMessage(),
                HttpStatus.CONFLICT.value()
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<ExceptionResponse> badCredentialsExceptionHandler(Exception exception) {
        ExceptionResponse response = new ExceptionResponse(
                exception.getClass().getName(),
                "not my exception!",
                exception.getMessage(),
                HttpStatus.UNAUTHORIZED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionResponse> authCredentialsExceptionHandler(JwtException exception) {
        ExceptionResponse response = new ExceptionResponse(
                exception.getClass().getName(),
                "JWT is incorrect!",
                exception.getMessage(),
                HttpStatus.UNAUTHORIZED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleInsufficientAuthenticationException(InsufficientAuthenticationException exception) {

        ExceptionResponse response = new ExceptionResponse(
                exception.getClass().getName(),
                "Insufficient",
                exception.getMessage(),
                HttpStatus.UNAUTHORIZED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
