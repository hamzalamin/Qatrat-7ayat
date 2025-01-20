package com.wora.qatrat7ayat.models.DTOs.city;

import jakarta.validation.constraints.NotBlank;

public record EmbeddedCityDto(
        @NotBlank Long id,
        @NotBlank String cityName
) {
}
