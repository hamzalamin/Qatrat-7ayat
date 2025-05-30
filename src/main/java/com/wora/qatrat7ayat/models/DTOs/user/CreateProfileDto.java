package com.wora.qatrat7ayat.models.DTOs.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateProfileDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        String psudoName,
        @NotBlank @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits") String phone,
        @NotBlank String bloodType,
        @NotNull Long cityId
) {
}
