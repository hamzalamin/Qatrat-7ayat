package com.wora.qatrat7ayat.security.services;

import com.wora.qatrat7ayat.security.config.jwt.JwtTokenProvider;
import com.wora.qatrat7ayat.security.mappers.UserMapper;
import com.wora.qatrat7ayat.security.models.DTOs.authUserDto.LoginDto;
import com.wora.qatrat7ayat.security.models.DTOs.authUserDto.RegisterDto;
import com.wora.qatrat7ayat.security.models.DTOs.userDtos.UserDto;
import com.wora.qatrat7ayat.security.models.entities.AuthenticatedUser;
import com.wora.qatrat7ayat.security.models.entities.Role;
import com.wora.qatrat7ayat.security.repositories.AuthenticatedUserRepository;
import com.wora.qatrat7ayat.security.repositories.RoleRepository;
import com.wora.qatrat7ayat.security.services.impl.IAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserService implements IAuthUserService {

    private final AuthenticatedUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;


    @Override
    public UserDto register(RegisterDto registerDto) {
        userRepository.findByEmail(registerDto.email())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("Email is already registered");
                });

        Role societyRole = roleRepository.findByName("society")
                .orElseThrow(() -> new IllegalArgumentException("Role 'society' not found"));

        AuthenticatedUser user = new AuthenticatedUser();
        user.setFirstName(registerDto.firstName());
        user.setLastName(registerDto.lastName());
        user.setPhone(registerDto.phone());
        user.setEmail(registerDto.email());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.setRole(societyRole);

        userRepository.save(user);

        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole().getName());

        return userMapper.toDto(user, token);
    }


    @Override
    public UserDto login(LoginDto loginDto) {
        AuthenticatedUser user = userRepository.findByEmail(loginDto.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole().getName());

        return userMapper.toDto(user, token);
    }

}
