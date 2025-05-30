package com.wora.qatrat7ayat.security.services.impl;


import com.wora.qatrat7ayat.security.models.entities.AuthenticatedUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final AuthenticatedUser user;

    public UserDetailsImpl(AuthenticatedUser user) {
        this.user = user;
    }

    public static UserDetailsImpl build(AuthenticatedUser user) {
        return new UserDetailsImpl(user);
    }

    public AuthenticatedUser getAuthenticatedUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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

    public Long getId() {
        return user.getId();
    }
}