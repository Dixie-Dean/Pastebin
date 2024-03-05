package com.dixie.pastebin.exception.advice;

import com.dixie.pastebin.security.authentication.model.response.ExceptionResponse;
import com.dixie.pastebin.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException() {
        ExceptionResponse response = new ExceptionResponse(
                "Email or password is incorrect.",
                HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistException() {
        ExceptionResponse response = new ExceptionResponse(
                "User with such email already registered.",
                HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidBearerTokenException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidBearerTokenException() {
        ExceptionResponse response = new ExceptionResponse(
                "The access token provided is expired, revoked, malformed or invalid for other reasons.",
                HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException() {
        ExceptionResponse response = new ExceptionResponse(
                "No permission.",
                HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleOtherException() {
        ExceptionResponse response = new ExceptionResponse(
                "A server error.",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
