package com.wora.qatrat7ayat.security.services.impl;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.exceptions.UserAlreadyExist;
import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.security.DTO.JwtResponse;
import com.wora.qatrat7ayat.security.DTO.LoginRequest;
import com.wora.qatrat7ayat.security.DTO.SignupRequest;
import com.wora.qatrat7ayat.security.DTO.SignupResponse;
import com.wora.qatrat7ayat.security.exception.UserProfileNotFound;
import com.wora.qatrat7ayat.security.exception.UserSuspendedException;
import com.wora.qatrat7ayat.security.mappers.AuthMapper;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.models.Role;
import com.wora.qatrat7ayat.security.repositories.AuthUserRepository;
import com.wora.qatrat7ayat.security.services.IAuthService;
import com.wora.qatrat7ayat.security.services.IRoleService;
import com.wora.qatrat7ayat.security.utils.JwtUtils;
import com.wora.qatrat7ayat.services.inter.ICityService;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final AuthUserRepository userRepository;
    private final AuthMapper authMapper;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final ICityService cityService;

    @Override
    public ResponseEntity<JwtResponse> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        User user = getUserByEmail(loginRequest.getEmail());

        if (!user.isSuspended()){
            throw new UserSuspendedException(loginRequest.getEmail());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }


    @Override
    public ProfileDto getAuthenticatedUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetailsImpl) {
                UserDetailsImpl userDetails = (UserDetailsImpl) principal;
                AuthenticatedUser authenticatedUser = getUserByEmail(userDetails.getUsername());
                return authMapper.toProfileDto(authenticatedUser);
            } else {
                throw new UserProfileNotFound("Authenticated user not found or not recognized.");
            }
        } else {
            throw new UserProfileNotFound("No authenticated user found.");
        }
    }

    @Override
    public SignupResponse registerUser(SignupRequest request) {
        if(existsByEmail(request.getEmail())){
            throw new UserAlreadyExist(request.getEmail());
        }
        City city = cityService.findCityEntity(request.getCityId());
        AuthenticatedUser authenticatedUser = authMapper.toEntity(request);
        authenticatedUser.setEmail(request.getEmail());
        authenticatedUser.setPassword(passwordEncoder.encode(request.getPassword()));
        authenticatedUser.setFirstName(request.getFirstName());
        authenticatedUser.setLastName(request.getLastName());
        authenticatedUser.setBloodType(request.getBloodType());
        authenticatedUser.setPhone(request.getPhone());
        authenticatedUser.setCity(city);
        authenticatedUser.setSuspended(true);
        Role role = roleService.findRoleByName("ROLE_USER");
        authenticatedUser.setRole(role);
        authenticatedUser.setGender(request.getGender());
        userRepository.save(authenticatedUser);
        return authMapper.toDto(authenticatedUser);
    }

    @Override
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public AuthenticatedUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Authenticated user", id));
    }

    @Override
    public AuthenticatedUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: ", email));
    }

    public List<SignupResponse> getAllUsers(){
        return userRepository.findAll().stream()
                .map(authMapper::toDto)
                .toList();
    }

}
