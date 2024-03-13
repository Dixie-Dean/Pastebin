package com.dixie.pastebin.exception;

import javax.naming.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
