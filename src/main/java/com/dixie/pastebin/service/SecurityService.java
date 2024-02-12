package com.dixie.pastebin.service;

import com.dixie.pastebin.dto.authentication.SignInData;
import com.dixie.pastebin.dto.authentication.RegisterData;
import com.dixie.pastebin.exception.UserAlreadyExistException;

public interface SecurityService {
    String register(RegisterData registerData) throws UserAlreadyExistException;
    String signIn(SignInData signInData);
}
