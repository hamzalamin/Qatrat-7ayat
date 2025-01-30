package com.wora.qatrat7ayat.models.DTOs.action.publicAction;

import com.wora.qatrat7ayat.models.enumes.BloodType;
import com.wora.qatrat7ayat.models.enumes.UrgencyLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePublicActionDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String psudoName,
        @NotBlank String phone,
        @NotNull BloodType bloodType,
        @NotBlank String city,
        @NotBlank String message,
        Float bloodVolume,
        UrgencyLevel urgencyLevel,
        String availabilityPeriod
) {
}
