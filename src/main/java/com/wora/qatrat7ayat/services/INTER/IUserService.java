package com.wora.qatrat7ayat.services.INTER;

import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.account.CreateUserAccountDto;
import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.UpdateProfileDto;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.security.DTO.SignupResponse;
import com.wora.qatrat7ayat.services.GenericService;

public interface IUserService {
    User createUserEntity(CreateProfileDto createProfileDto);
}
