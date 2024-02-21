package com.dixie.pastebin.service.implementation;

import com.dixie.pastebin.dto.authentication.RegisterData;
import com.dixie.pastebin.dto.authentication.SignInData;
import com.dixie.pastebin.entity.PastebinUser;
import com.dixie.pastebin.exception.UserAlreadyExistException;
import com.dixie.pastebin.exception.UserNotFoundException;
import com.dixie.pastebin.repository.UserRepository;
import com.dixie.pastebin.security.role.Role;
import com.dixie.pastebin.service.SecurityService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;

    public SecurityServiceImpl(UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               AuthenticationProvider authenticationProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
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
        if (!userRepository.existsByEmail(signInData.getEmail())) {
            throw new UserNotFoundException("User with such email doesn't exist!");
        }

        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInData.getEmail(),
                        signInData.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "Welcome!";
    }
}
