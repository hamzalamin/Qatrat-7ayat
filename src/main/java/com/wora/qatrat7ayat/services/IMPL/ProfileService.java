package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.exceptions.ProfileAlreadyCompletedException;
import com.wora.qatrat7ayat.mappers.ProfileMapper;
import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.UpdateProfileDto;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.models.enumes.BloodType;
import com.wora.qatrat7ayat.repositories.ProfileRepository;
import com.wora.qatrat7ayat.services.INTER.ICityService;
import com.wora.qatrat7ayat.services.INTER.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final ICityService cityService;

    @Override
    public ProfileDto create(CreateProfileDto createProfileDto) {
        User userProfile = profileMapper.toEntity(createProfileDto);
        if (userProfile.isProfileCompleted()) {
            throw new ProfileAlreadyCompletedException("Profile is already completed");
        }
        userProfile.setCreatedAt(LocalDateTime.now().toString());
        userProfile.setProfileCompleted(true);
        User savedProfile = profileRepository.save(userProfile);
        return profileMapper.toDto(savedProfile);
    }

    @Override
    public ProfileDto findById(Long id) {
        User profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
        return profileMapper.toDto(profile);
    }

    @Override
    public ProfileDto update(UpdateProfileDto updateProfileDto, Long id) {
        User profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
        City city = cityService.findCityEntity(updateProfileDto.city_id());


        User updateProfile = profile.toBuilder()
                .firstName(updateProfileDto.firstName())
                .lastName(updateProfileDto.lastName())
                .phone(updateProfileDto.phone())
                .bloodType(BloodType.valueOf(updateProfileDto.bloodType()))
                .psudoName(updateProfileDto.psudoName())
                .city(city)
                .updatedAt(LocalDateTime.now().toString())
                .build();
        User savedProfile = profileRepository.save(updateProfile);
        return profileMapper.toDto(savedProfile);
    }

    @Override
    public List<ProfileDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return profileRepository.findAll(pageRequest).stream()
                .map(profileMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        User profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
        profileRepository.delete(profile);
    }

    @Override
    public User findUserEntity(Long id){
        return profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
    }
}
