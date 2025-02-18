package com.wora.qatrat7ayat.security.services;

import com.wora.qatrat7ayat.security.DTO.JwtResponse;
import com.wora.qatrat7ayat.security.DTO.LoginRequest;
import com.wora.qatrat7ayat.security.DTO.SignupRequest;
import com.wora.qatrat7ayat.security.DTO.SignupResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<JwtResponse> authenticateUser(LoginRequest loginRequest);
    SignupResponse registerUser(SignupRequest request);
}
