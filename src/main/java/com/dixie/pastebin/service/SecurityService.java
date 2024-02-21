package com.dixie.pastebin.service;

import com.dixie.pastebin.dto.authentication.SignInData;
import com.dixie.pastebin.dto.authentication.RegisterData;
import com.dixie.pastebin.dto.authentication.SignInResponse;
import com.dixie.pastebin.exception.UserAlreadyExistException;
import org.springframework.http.ResponseEntity;

public interface SecurityService {
    String register(RegisterData registerData) throws UserAlreadyExistException;
    ResponseEntity<SignInResponse> signIn(SignInData signInData);
}
