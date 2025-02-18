package com.wora.qatrat7ayat.security.mappers;

import com.wora.qatrat7ayat.security.DTO.SignupRequest;
import com.wora.qatrat7ayat.security.DTO.SignupResponse;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthenticatedUser toEntity(SignupResponse signupResponse);
    AuthenticatedUser toEntity(SignupRequest signupRequest);

    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "bloodType", source = "bloodType")
    SignupResponse toDto(AuthenticatedUser authenticatedUser);
}
