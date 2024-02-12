package com.dixie.pastebin.security.user;

import com.dixie.pastebin.entity.PastebinUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class PastebinUserDetails implements UserDetails {
    private final PastebinUser pastebinUser;

    @Override
    public String getPassword() {
        return pastebinUser.getPassword();
    }

    @Override
    public String getUsername() {
        return pastebinUser.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
