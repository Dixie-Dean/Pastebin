package com.dixie.pastebin.controller;

import com.dixie.pastebin.dto.auth.SignInData;
import com.dixie.pastebin.dto.auth.RegisterData;
import com.dixie.pastebin.exception.UserAlreadyExistException;
import com.dixie.pastebin.service.SecurityService;
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

    @PostMapping("/signing-in")
    public String signIn(@RequestBody SignInData signInData) {
        return securityService.signIn(signInData);
    }

}
