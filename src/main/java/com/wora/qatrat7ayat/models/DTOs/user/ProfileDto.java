package com.wora.qatrat7ayat.models.DTOs.user;

import com.wora.qatrat7ayat.models.DTOs.city.EmbeddedCityDto;
import com.wora.qatrat7ayat.models.enumes.BloodType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ProfileDto(
        Long id,
        @NotBlank String firstName,
        @NotBlank String lastName,
        String psudoName,
        @NotBlank String phone,
        @NotBlank BloodType bloodType,
        Date createdAt,
        Date updatedAt,
        @NotNull EmbeddedCityDto city
) {
}
