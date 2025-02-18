package com.wora.qatrat7ayat.security.services;

import com.wora.qatrat7ayat.security.DTO.LoginRequest;
import com.wora.qatrat7ayat.security.DTO.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> registerUser(SignupRequest request);
}
