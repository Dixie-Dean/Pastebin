package com.dixie.pastebin.service.implementation;

import com.dixie.pastebin.dto.authentication.RegisterData;
import com.dixie.pastebin.dto.authentication.SignInData;
import com.dixie.pastebin.entity.PastebinUser;
import com.dixie.pastebin.exception.UserAlreadyExistException;
import com.dixie.pastebin.repository.UserRepository;
import com.dixie.pastebin.security.role.Role;
import com.dixie.pastebin.security.user.PastebinUserDetails;
import com.dixie.pastebin.service.SecurityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationProvider authenticationProvider;

    public SecurityServiceImpl(UserRepository userRepository,
                               PasswordEncoder passwordEncoder/*,
                               AuthenticationProvider authenticationProvider*/) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
//        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public String register(RegisterData registerData) throws UserAlreadyExistException {
        if (userRepository.existsByEmail(registerData.getEmail())) {
            throw new UserAlreadyExistException("User with such email already registered!");
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PastebinUserDetails principal = (PastebinUserDetails) authentication.getPrincipal();
        return String.format("Welcome, %s!", principal.getPastebinUser().getUsername());
    }
}
