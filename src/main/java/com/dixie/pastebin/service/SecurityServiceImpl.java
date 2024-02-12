package com.dixie.pastebin.service;

import com.dixie.pastebin.dto.auth.SignInData;
import com.dixie.pastebin.dto.auth.RegisterData;
import com.dixie.pastebin.entity.PastebinUser;
import com.dixie.pastebin.exception.UserAlreadyExistException;
import com.dixie.pastebin.exception.UserNotFoundException;
import com.dixie.pastebin.repository.SecurityRepository;
import com.dixie.pastebin.security.role.Role;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final SecurityRepository securityRepository;

    public SecurityServiceImpl(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Override
    public String register(RegisterData registerData) throws UserAlreadyExistException {
        if (securityRepository.existsByEmail(registerData.getEmail())) {
            throw new UserAlreadyExistException("User with such email is already registered!");
        }

        PastebinUser user = new PastebinUser();
        user.setUsername(registerData.getUsername());
        user.setLastname(registerData.getLastname());
        user.setEmail(registerData.getEmail());
        user.setRole(Role.USER);

        securityRepository.save(user);
        return "Registration successful!";
    }

    @Override
    public String signIn(SignInData signInData) {
        if (!securityRepository.existsByEmail(signInData.getEmail())) {
            throw new UserNotFoundException("User with such email doesn't exist!");
        }

        return "Welcome!";
    }
}
