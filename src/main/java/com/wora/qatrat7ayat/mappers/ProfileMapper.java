package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.models.DTOs.User.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.User.ProfileDto;
import com.wora.qatrat7ayat.models.DTOs.User.UpdateProfileDto;
import com.wora.qatrat7ayat.models.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper extends GenericMapper<User, ProfileDto> {
    User toEntity(ProfileDto dto);
    User toEntity(CreateProfileDto dto);
    User toEntity(UpdateProfileDto dto);
    ProfileDto toDto(User user);
}
