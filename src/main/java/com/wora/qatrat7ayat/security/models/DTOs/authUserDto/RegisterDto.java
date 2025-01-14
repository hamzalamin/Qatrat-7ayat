package com.wora.qatrat7ayat.security.models.DTOs.authUserDto;

public record RegisterDto(
        String firstName,
        String lastName,
        String phone,
        String email,
        String password
) {
}