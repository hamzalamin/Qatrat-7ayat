package com.wora.qatrat7ayat.security.services.impl;

import com.wora.qatrat7ayat.security.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtils jwtUtils;

    public String generateJwtToken(Authentication authentication) {
        return jwtUtils.generateJwtToken(authentication);
    }

    public boolean validateJwtToken(String authToken) {
        return jwtUtils.validateJwtToken(authToken);
    }

    public String getUserNameFromJwtToken(String token) {
        return jwtUtils.getUserNameFromJwtToken(token);
    }
}