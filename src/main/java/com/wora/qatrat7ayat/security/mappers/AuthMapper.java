package com.wora.qatrat7ayat.security.mappers;

import com.wora.qatrat7ayat.models.DTOs.account.CreateUserAccountDto;
import com.wora.qatrat7ayat.models.DTOs.account.UpdateUserAccountDto;
import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import com.wora.qatrat7ayat.security.DTO.SignupRequest;
import com.wora.qatrat7ayat.security.DTO.SignupResponse;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthenticatedUser toEntity(SignupResponse signupResponse);
    AuthenticatedUser toEntity(SignupRequest signupRequest);
    AuthenticatedUser toEntity(CreateUserAccountDto signupRequest);
    AuthenticatedUser toEntity(UpdateUserAccountDto signupRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "bloodType", source = "bloodType")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "role", source = "role")
    SignupResponse toDto(AuthenticatedUser authenticatedUser);
    ProfileDto toProfileDto(AuthenticatedUser authenticatedUser);
}
