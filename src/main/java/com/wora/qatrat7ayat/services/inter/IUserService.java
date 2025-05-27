package com.wora.qatrat7ayat.services.inter;

import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import com.wora.qatrat7ayat.models.entities.User;

public interface IUserService {
    User createUserEntity(CreateProfileDto createProfileDto);
}
