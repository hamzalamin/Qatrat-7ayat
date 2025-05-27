package com.wora.qatrat7ayat.models.DTOs.user;

import com.wora.qatrat7ayat.models.DTOs.city.EmbeddedCityDto;
import com.wora.qatrat7ayat.models.enumes.BloodType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record EmbeddedProfileDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        String psudoName,
        @NotBlank String phone,
        @NotBlank BloodType bloodType,
        @NotBlank String availabilityMessage,
        @NotBlank EmbeddedCityDto city
) {
}
