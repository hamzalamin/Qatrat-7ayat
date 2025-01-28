package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.mappers.ProfileMapper;
import com.wora.qatrat7ayat.models.DTOs.User.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.User.ProfileDto;
import com.wora.qatrat7ayat.models.DTOs.User.UpdateProfileDto;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.repositories.ProfileRepository;
import com.wora.qatrat7ayat.services.INTER.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ProfileDto create(CreateProfileDto createProfileDto) {
        User userProfile = profileMapper.toEntity(createProfileDto);
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


        User updateProfile = profile.toBuilder()
                .firstName(updateProfileDto.firstName())
                .lastName(updateProfileDto.lastName())
                .phone(updateProfileDto.phone())
                .bloodType(updateProfileDto.bloodType())
                .psudoName(updateProfileDto.psudoName())
                .availabilityMessage(updateProfileDto.availabilityMessage())
                //tatkmel hadchi laaakher 3ndaaak tensssssah
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
