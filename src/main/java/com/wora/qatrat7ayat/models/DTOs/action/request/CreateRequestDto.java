package com.wora.qatrat7ayat.models.DTOs.action.request;

import com.wora.qatrat7ayat.models.enumes.BloodType;
import com.wora.qatrat7ayat.models.enumes.UrgencyLevel;

public record CreateRequestDto(
        String firstName,
        String lastName,
        String psudoName,
        String phone,
        BloodType bloodType,
        String city,
        String message,
        Float bloodVolume,
        UrgencyLevel urgencyLevel
) {
}
