package com.wora.qatrat7ayat.models.DTOs.action.AuthAction;

import com.wora.qatrat7ayat.models.enumes.UrgencyLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateAuthActionDto(
        @NotBlank String message,
        @NotNull Float bloodVolume,
        @NotNull UrgencyLevel urgencyLevel,
        @NotBlank String availabilityPeriod
) {
}
