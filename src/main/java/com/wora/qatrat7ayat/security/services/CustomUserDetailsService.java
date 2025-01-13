package com.wora.qatrat7ayat.security.services;

import com.wora.qatrat7ayat.security.models.entities.AuthenticatedUser;
import com.wora.qatrat7ayat.security.repositories.AuthenticatedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthenticatedUserRepository authenticatedUserRepository;

    @Autowired
    public CustomUserDetailsService(AuthenticatedUserRepository authenticatedUserRepository) {
        this.authenticatedUserRepository = authenticatedUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        AuthenticatedUser user = authenticatedUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
