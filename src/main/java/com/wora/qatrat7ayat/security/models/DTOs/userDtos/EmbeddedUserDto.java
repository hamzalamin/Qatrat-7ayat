package com.wora.qatrat7ayat.security.models.DTOs.userDtos;

public record EmbeddedUserDto(
        Long id,
        String firstName,
        String lastName,
        String psudoName,
        String phone,
        String bloodType,
        String city,
        String availabilityMessage,
        String createdAt,
        String updatedAt
) {
}
