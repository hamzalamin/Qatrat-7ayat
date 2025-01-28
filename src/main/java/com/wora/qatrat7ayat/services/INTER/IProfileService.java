package com.wora.qatrat7ayat.services.INTER;

import com.wora.qatrat7ayat.models.DTOs.User.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.User.ProfileDto;
import com.wora.qatrat7ayat.models.DTOs.User.UpdateProfileDto;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.services.GenericService;

public interface IProfileService extends GenericService<CreateProfileDto, UpdateProfileDto, ProfileDto, Long> {
    User findUserEntity(Long id);
}
