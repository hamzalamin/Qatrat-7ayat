package com.wora.qatrat7ayat.models.DTOs.User;

import com.wora.qatrat7ayat.models.DTOs.city.EmbeddedCityDto;
import com.wora.qatrat7ayat.models.enumes.BloodType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfileDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String psudoName,
        @NotBlank String phone,
        @NotBlank BloodType bloodType,
        @NotBlank String availabilityMessage,
        @NotNull EmbeddedCityDto city
) {
}
