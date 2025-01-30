package com.wora.qatrat7ayat.models.DTOs.action;

import com.wora.qatrat7ayat.models.DTOs.user.EmbeddedProfileDto;
import com.wora.qatrat7ayat.models.enumes.UrgencyLevel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ActionDto(
        EmbeddedProfileDto user,
        String message,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        BigDecimal bloodVolume,
        UrgencyLevel urgencyLevel,
        String availabilityPeriod
) {
}
