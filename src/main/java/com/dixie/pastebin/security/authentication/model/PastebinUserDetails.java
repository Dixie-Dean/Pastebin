package com.dixie.pastebin.security.authentication.model;

import com.dixie.pastebin.security.authentication.model.entity.PastebinUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class PastebinUserDetails implements UserDetails {

    private final PastebinUser pastebinUser;

    @Override
    public String getUsername() {
        return pastebinUser.getEmail();
    }

    @Override
    public String getPassword() {
        return pastebinUser.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(pastebinUser.getRole().split(", ")).map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
