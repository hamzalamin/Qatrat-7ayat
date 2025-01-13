package com.wora.qatrat7ayat.security.services;

import com.wora.qatrat7ayat.security.config.AuthenticatedUserDetails;
import com.wora.qatrat7ayat.security.models.entities.AuthenticatedUser;
import com.wora.qatrat7ayat.security.repositories.AuthenticatedUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AuthenticatedUserRepository userRepository;

    public CustomUserDetailsService(AuthenticatedUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        AuthenticatedUser user = userRepository.findByEmail(email);
        return new AuthenticatedUserDetails(user);
    }
}