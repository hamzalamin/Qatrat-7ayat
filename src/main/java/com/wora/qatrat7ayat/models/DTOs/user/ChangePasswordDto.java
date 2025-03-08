package com.wora.qatrat7ayat.models.DTOs.user;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordDto(
        @NotBlank String oldPassword,
        @NotBlank String newPassword
) {
}
