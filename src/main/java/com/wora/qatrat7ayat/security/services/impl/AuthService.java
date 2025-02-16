package com.wora.qatrat7ayat.security.services.impl;

import com.wora.qatrat7ayat.security.DTO.JwtResponse;
import com.wora.qatrat7ayat.security.DTO.LoginRequest;
import com.wora.qatrat7ayat.security.DTO.SignupRequest;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.models.Role;
import com.wora.qatrat7ayat.security.repositories.ProfileRepository;
import com.wora.qatrat7ayat.security.services.IRoleService;
import com.wora.qatrat7ayat.security.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ProfileRepository userRepository;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }


    public ResponseEntity<?> registerUser(SignupRequest request) {
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setEmail(request.getEmail());
        authenticatedUser.setPassword(passwordEncoder.encode(request.getPassword()));
        authenticatedUser.setFirstName(request.getFirstName());
        authenticatedUser.setLastName(request.getLastName());
        Role role = roleService.findRoleByName("ROLE_USER");
        authenticatedUser.setRole(role);
        userRepository.save(authenticatedUser);

        return ResponseEntity.ok(new MessageFormat("User registered successfully!"));
    }

}
