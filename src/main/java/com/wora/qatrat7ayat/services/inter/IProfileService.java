package com.wora.qatrat7ayat.services.inter;

import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.UpdateProfileDto;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.services.GenericService;

public interface IProfileService extends GenericService<CreateProfileDto, UpdateProfileDto, ProfileDto, Long> {
    User findUserEntity(Long id);
    void changePassword(Long id, String oldPassword, String newPassword);
}
