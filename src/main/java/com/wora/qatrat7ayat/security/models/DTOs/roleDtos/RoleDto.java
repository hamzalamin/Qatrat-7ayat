package com.wora.qatrat7ayat.security.models.DTOs.roleDtos;

import com.wora.qatrat7ayat.security.models.DTOs.userDtos.EmbeddedUserDto;

import java.util.List;

public record RoleDto(
        Long id,
        String name,
        List<EmbeddedUserDto> users
) {
}
