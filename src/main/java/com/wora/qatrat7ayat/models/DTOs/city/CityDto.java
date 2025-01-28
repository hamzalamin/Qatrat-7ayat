package com.wora.qatrat7ayat.models.DTOs.city;

import jakarta.validation.constraints.NotBlank;

public record CityDto(
        @NotBlank Long id,
        @NotBlank String cityName
) {
}
