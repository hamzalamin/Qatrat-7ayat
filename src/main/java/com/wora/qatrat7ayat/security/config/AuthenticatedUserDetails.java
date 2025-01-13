package com.wora.qatrat7ayat.security.config;

import com.wora.qatrat7ayat.security.models.entities.AuthenticatedUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthenticatedUserDetails extends User implements UserDetails {


    private final AuthenticatedUser authenticatedUser;

    public AuthenticatedUserDetails(AuthenticatedUser authenticatedUser) {
        super(authenticatedUser.getEmail(), authenticatedUser.getPassword(), mapRolesToAuthorities(authenticatedUser));
        this.authenticatedUser = authenticatedUser;
    }

    private static Collection<? extends GrantedAuthority> mapRolesToAuthorities(AuthenticatedUser user) {
        return List.of(() -> "ROLE_" + user.getRole().getName());
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
