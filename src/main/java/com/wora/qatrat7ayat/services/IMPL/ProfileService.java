package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.mappers.ProfileMapper;
import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.UpdateProfileDto;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.models.enumes.BloodType;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.repositories.ProfileRepository;
import com.wora.qatrat7ayat.services.INTER.ICityService;
import com.wora.qatrat7ayat.services.INTER.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final ICityService cityService;

    @Override
    public ProfileDto create(CreateProfileDto createProfileDto) {
        return null;
    }

    @Override
    public ProfileDto findById(Long id) {
        User profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
        return profileMapper.toDto(profile);
    }

    @Override
    @Transactional
    public ProfileDto update(UpdateProfileDto updateProfileDto, Long id) {
        AuthenticatedUser profile = profileRepository.findById(id)
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
        return profileRepository.findAll(pageRequest).stream()
                .map(profileMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        AuthenticatedUser profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
        profileRepository.delete(profile);
    }

    @Override
    public User findUserEntity(Long id){
        return profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
    }
}
