package com.wora.qatrat7ayat.services.impl;

import com.wora.qatrat7ayat.mappers.ProfileMapper;
import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.security.repositories.UserRepository;
import com.wora.qatrat7ayat.services.inter.ICityService;
import com.wora.qatrat7ayat.services.inter.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ProfileMapper userMapper;
    private final ICityService cityService;

    @Override
    public User createUserEntity(CreateProfileDto createProfileDto) {
        User user = userMapper.toEntity(createProfileDto);
        City city = cityService.findCityEntity(createProfileDto.cityId());
        user.setCity(city);
        return userRepository.save(user);
    }
}
