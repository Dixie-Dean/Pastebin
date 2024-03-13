package com.dixie.pastebin.security.authentication.controller;

import com.dixie.pastebin.security.authentication.model.dto.RegisterData;
import com.dixie.pastebin.security.authentication.model.dto.SignInData;
import com.dixie.pastebin.security.authentication.model.response.SignInResponse;
import com.dixie.pastebin.exception.UserAlreadyExistException;
import com.dixie.pastebin.security.authentication.service.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    private final SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/registration")
    public String register(@RequestBody RegisterData registerData) throws UserAlreadyExistException {
        return securityService.register(registerData);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInData signInData) {
        return securityService.signIn(signInData);
    }
}
