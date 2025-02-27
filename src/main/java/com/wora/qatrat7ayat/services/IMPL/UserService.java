package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.exceptions.UserAlreadyExist;
import com.wora.qatrat7ayat.mappers.ProfileMapper;
import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.CreateUserAccountDto;
import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.UpdateProfileDto;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.security.DTO.SignupRequest;
import com.wora.qatrat7ayat.security.DTO.SignupResponse;
import com.wora.qatrat7ayat.security.mappers.AuthMapper;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.models.Role;
import com.wora.qatrat7ayat.security.repositories.UserRepository;
import com.wora.qatrat7ayat.security.services.IAuthService;
import com.wora.qatrat7ayat.security.services.IRoleService;
import com.wora.qatrat7ayat.services.INTER.ICityService;
import com.wora.qatrat7ayat.services.INTER.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ProfileMapper userMapper;
    private final ICityService cityService;
    private final AuthMapper authMapper;
    private final IRoleService roleService;
    private final IAuthService authService;

    @Override
    public User createUserEntity(CreateProfileDto createProfileDto) {
        User user = userMapper.toEntity(createProfileDto);
        City city = cityService.findCityEntity(createProfileDto.cityId());
        user.setCity(city);
        return userRepository.save(user);
    }

    @Override
    public SignupResponse createUserAccount(CreateUserAccountDto signupRequest) {
        if (authService.existsByEmail(signupRequest.getEmail())) {
            throw new UserAlreadyExist(signupRequest.getEmail());
        }
        Role role = roleService.findRoleById(signupRequest.getRoleId());
        AuthenticatedUser user = authMapper.toEntity(signupRequest);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(signupRequest.getPassword());
        user.setPassword(hashedPassword);
        user.setRole(role);
        AuthenticatedUser savedUser = userRepository.save(user);
        return authMapper.toDto(savedUser);
    }

    @Override
    public boolean toggleSuspension(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));

        user.setSuspended(!user.isSuspended());
        userRepository.save(user);

        return user.isSuspended();
    }


    @Override
    public ProfileDto create(CreateProfileDto createProfileDto) {
        return null;
    }

    @Override
    public ProfileDto findById(Long id) {
        return null;
    }

    @Override
    public ProfileDto update(UpdateProfileDto updateProfileDto, Long id) {
        return null;
    }

    @Override
    public List<ProfileDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
}
