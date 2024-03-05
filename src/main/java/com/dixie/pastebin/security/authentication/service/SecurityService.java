package com.dixie.pastebin.security.authentication.service;

import com.dixie.pastebin.security.authentication.model.dto.RegisterData;
import com.dixie.pastebin.security.authentication.model.dto.SignInData;
import com.dixie.pastebin.security.authentication.model.response.SignInResponse;
import com.dixie.pastebin.exception.UserAlreadyExistException;
import org.springframework.http.ResponseEntity;

public interface SecurityService {
    String register(RegisterData registerData) throws UserAlreadyExistException;
    ResponseEntity<SignInResponse> signIn(SignInData signInData);
}
