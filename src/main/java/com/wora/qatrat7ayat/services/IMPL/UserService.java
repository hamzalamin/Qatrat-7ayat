package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.mappers.ProfileMapper;
import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.UpdateProfileDto;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.security.repositories.UserRepository;
import com.wora.qatrat7ayat.services.INTER.ICityService;
import com.wora.qatrat7ayat.services.INTER.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ProfileMapper userMapper;
    private final ICityService cityService;

    @Override
    public User createUserEntity(CreateProfileDto createProfileDto) {
        User user = userMapper.toEntity(createProfileDto);
        City city = cityService.findCityEntity(createProfileDto.city_id());
        user.setCity(city);
        return userRepository.save(user);
    }

    @Override
    public ProfileDto create(CreateProfileDto createProfileDto) {
        User user = userMapper.toEntity(createProfileDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public ProfileDto findById(Long aLong) {
        return null;
    }

    @Override
    public ProfileDto update(UpdateProfileDto updateProfileDto, Long aLong) {
        return null;
    }

    @Override
    public List<ProfileDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long aLong) {

    }
}
