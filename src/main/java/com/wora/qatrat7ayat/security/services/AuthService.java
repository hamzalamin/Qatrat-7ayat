package com.wora.qatrat7ayat.security.services;

import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.repositories.CityRepository;
import com.wora.qatrat7ayat.security.DTO.JwtResponse;
import com.wora.qatrat7ayat.security.DTO.LoginRequest;
import com.wora.qatrat7ayat.security.DTO.SignupRequest;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.models.Role;
import com.wora.qatrat7ayat.security.models.enume.ERole;
import com.wora.qatrat7ayat.security.repositories.RoleRepository;
import com.wora.qatrat7ayat.security.repositories.UserRepository;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CityRepository cityRepository;
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

        authenticatedUser.setBloodType(request.getBloodType());
        authenticatedUser.setFirstName(request.getFirstName());
        authenticatedUser.setLastName(request.getLastName());
        authenticatedUser.setPhone(request.getPhone());
        authenticatedUser.setAvailabilityMessage(request.getAvailabilityMessage());
        authenticatedUser.setCreatedAt(LocalDateTime.now().toString());
        authenticatedUser.setUpdatedAt(LocalDateTime.now().toString());

        if (request.getCityId() != null) {
            City city = cityRepository.findById(request.getCityId())
                    .orElseThrow(() -> new IllegalArgumentException("City not found"));
            authenticatedUser.setCity(city);
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        authenticatedUser.setRole(role);

        userRepository.save(authenticatedUser);
        return ResponseEntity.ok(new MessageFormat("User registered successfully!"));
    }
}
