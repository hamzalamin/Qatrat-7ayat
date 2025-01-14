package com.wora.qatrat7ayat.security.mappers;

import com.wora.qatrat7ayat.security.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.security.models.DTOs.authUserDto.LoginDto;
import com.wora.qatrat7ayat.security.models.DTOs.authUserDto.RegisterDto;
import com.wora.qatrat7ayat.security.models.DTOs.roleDtos.RoleDto;
import com.wora.qatrat7ayat.security.models.DTOs.userDtos.CreateUserDto;
import com.wora.qatrat7ayat.security.models.DTOs.userDtos.UpdateUserDto;
import com.wora.qatrat7ayat.security.models.DTOs.userDtos.UserDto;
import com.wora.qatrat7ayat.security.models.entities.AuthenticatedUser;
import com.wora.qatrat7ayat.security.models.entities.Role;
import com.wora.qatrat7ayat.security.models.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<Role, RoleDto> {
    User toEntity(UserDto dto);
    User toEntity(CreateUserDto dto);
    User toEntity(UpdateUserDto dto);
    UserDto toDto(User user);
    User toEntity(RegisterDto dto);
    User toEntity(LoginDto dto);
    UserDto toDto(AuthenticatedUser user, String token);
}
