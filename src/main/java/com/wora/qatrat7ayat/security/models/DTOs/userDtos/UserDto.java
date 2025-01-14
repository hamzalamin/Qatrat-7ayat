package com.wora.qatrat7ayat.security.models.DTOs.userDtos;

import com.wora.qatrat7ayat.security.models.DTOs.roleDtos.EmbeddedRoleDto;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String psudoName,
        String phone,
        String bloodType,
        String city,
        String availabilityMessage,
        String createdAt,
        String updatedAt,
        EmbeddedRoleDto role
) {
}
