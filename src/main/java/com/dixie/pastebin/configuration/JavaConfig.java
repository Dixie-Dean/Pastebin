package com.dixie.pastebin.configuration;

import com.dixie.pastebin.repository.UserRepository;
import com.dixie.pastebin.security.jwt.JwtAuthenticationFilter;
import com.dixie.pastebin.security.jwt.JwtManager;
import com.dixie.pastebin.security.user.PastebinUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class JavaConfig {
    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() throws UsernameNotFoundException {
        return email -> userRepository.findPastebinUserByEmail(email).map(PastebinUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with such email doesn't exist!"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }

    @Bean
    public JwtManager jwtManager() {
        return new JwtManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(userDetailsService(), jwtManager());
    }

}
