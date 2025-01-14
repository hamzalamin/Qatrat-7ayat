package com.wora.qatrat7ayat.security.services.impl;

import com.wora.qatrat7ayat.security.models.DTOs.authUserDto.LoginDto;
import com.wora.qatrat7ayat.security.models.DTOs.authUserDto.RegisterDto;
import com.wora.qatrat7ayat.security.models.DTOs.userDtos.UserDto;

public interface IAuthUserService {
    UserDto register(RegisterDto registerDto);
    UserDto login(LoginDto loginDto);
}
