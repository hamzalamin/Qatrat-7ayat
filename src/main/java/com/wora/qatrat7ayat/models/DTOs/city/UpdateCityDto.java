package com.wora.qatrat7ayat.models.DTOs.city;

import jakarta.validation.constraints.NotBlank;

public record UpdateCityDto(
        @NotBlank String cityName
) {
}
