package com.wora.qatrat7ayat.models.DTOs.city;

import jakarta.validation.constraints.NotBlank;

public record CreateCityDto(
        @NotBlank String cityName
) {
}
