package com.dixie.pastebin.service;

import com.dixie.pastebin.dto.authentication.SignInData;
import com.dixie.pastebin.dto.authentication.RegisterData;
import com.dixie.pastebin.entity.PastebinUser;
import com.dixie.pastebin.exception.UserAlreadyExistException;
import com.dixie.pastebin.exception.UserNotFoundException;
import com.dixie.pastebin.repository.UserRepository;
import com.dixie.pastebin.security.role.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String register(RegisterData registerData) throws UserAlreadyExistException {
        if (userRepository.existsByEmail(registerData.getEmail())) {
            throw new UserAlreadyExistException("User with such email is already registered!");
        }

        PastebinUser user = new PastebinUser();
        user.setUsername(registerData.getUsername());
        user.setLastname(registerData.getLastname());
        user.setEmail(registerData.getEmail());
        user.setPassword(passwordEncoder.encode(registerData.getPassword()));
        user.setRole(String.valueOf(Role.USER));

        userRepository.save(user);
        return "Registration successful!";
    }

    @Override
    public String signIn(SignInData signInData) {
        if (!userRepository.existsByEmail(signInData.getEmail())) {
            throw new UserNotFoundException("User with such email doesn't exist!");
        }

        return "Welcome!";
    }
}
