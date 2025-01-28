package com.wora.qatrat7ayat.models.DTOs.User;

import com.wora.qatrat7ayat.models.DTOs.city.EmbeddedCityDto;
import com.wora.qatrat7ayat.models.enumes.BloodType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProfileDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String psudoName,
        @NotBlank String phone,
        @NotBlank BloodType bloodType,
        @NotBlank String availabilityMessage,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        @NotNull EmbeddedCityDto city
) {
}
