package com.dixie.pastebin.service;

import com.dixie.pastebin.dto.auth.SignInData;
import com.dixie.pastebin.dto.auth.RegisterData;
import com.dixie.pastebin.exception.UserAlreadyExistException;

public interface SecurityService {
    String register(RegisterData registerData) throws UserAlreadyExistException;
    String signIn(SignInData signInData);
}
