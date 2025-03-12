package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.exceptions.OldPasswordIncorrectException;
import com.wora.qatrat7ayat.mappers.ProfileMapper;
import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.UpdateProfileDto;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.models.enumes.BloodType;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.repositories.AuthUserRepository;
import com.wora.qatrat7ayat.security.repositories.UserRepository;
import com.wora.qatrat7ayat.security.services.IAuthService;
import com.wora.qatrat7ayat.services.INTER.ICityService;
import com.wora.qatrat7ayat.services.INTER.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {
    private final AuthUserRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;
    private final ICityService cityService;
    private final AuthenticationManager authenticationManager;
    private final IAuthService authService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ProfileDto create(CreateProfileDto createProfileDto) {
        return null;
    }

    @Override
    public ProfileDto findById(Long id) {
        User profile = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
        return profileMapper.toDto(profile);
    }

    @Override
    @Transactional
    public ProfileDto update(UpdateProfileDto updateProfileDto, Long id) {
        User profile = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
        City city = cityService.findCityEntity(updateProfileDto.cityId());

        profile.setFirstName(updateProfileDto.firstName())
                .setLastName(updateProfileDto.lastName())
                .setPhone(updateProfileDto.phone())
                .setBloodType(BloodType.valueOf(updateProfileDto.bloodType().toString()))
                .setPsudoName(updateProfileDto.psudoName())
                .setCity(city);

        return profileMapper.toDto(profile);
    }

    @Override
    public List<ProfileDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return userRepository.findAll(pageRequest).stream()
                .map(profileMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        User profile = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
        userRepository.delete(profile);
    }

    @Override
    public User findUserEntity(Long id){
        return profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
    }

    @Override
    public void changePassword(Long id, String oldPassword, String newPassword) {
        AuthenticatedUser user = authService.getUserById(id);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), oldPassword));

        String passwordUser = user.getPassword();
        boolean passwordCheck = isSamePassword(passwordUser, oldPassword);
        if (!passwordCheck){
            throw new OldPasswordIncorrectException("Old password is incorrect");
        }
        if (!authentication.isAuthenticated()) {
            throw new OldPasswordIncorrectException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    private boolean isSamePassword(String passwordUser, String oldPassword){
        if (!passwordUser.equals(oldPassword)){
            return false;
        }
        return true;
    }

}
