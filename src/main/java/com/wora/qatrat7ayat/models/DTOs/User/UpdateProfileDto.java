package com.wora.qatrat7ayat.models.DTOs.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateProfileDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String psudoName,
        @NotBlank String phone,
        @NotBlank String bloodType,
        @NotBlank String availabilityMessage,
        @NotNull Long city_id
) {
}
