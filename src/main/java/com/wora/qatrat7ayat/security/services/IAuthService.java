package com.wora.qatrat7ayat.security.services;

import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import com.wora.qatrat7ayat.security.models.dtos.JwtResponse;
import com.wora.qatrat7ayat.security.models.dtos.LoginRequest;
import com.wora.qatrat7ayat.security.models.dtos.SignupRequest;
import com.wora.qatrat7ayat.security.models.dtos.SignupResponse;
import com.wora.qatrat7ayat.security.models.entities.AuthenticatedUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAuthService {
    ResponseEntity<JwtResponse> authenticateUser(LoginRequest loginRequest);
    SignupResponse registerUser(SignupRequest request);
    boolean existsByEmail(String email);
    AuthenticatedUser getUserById(Long id);
    AuthenticatedUser getUserByEmail(String email);
    ProfileDto getAuthenticatedUserProfile();
    List<SignupResponse> getAllUsers();
}
