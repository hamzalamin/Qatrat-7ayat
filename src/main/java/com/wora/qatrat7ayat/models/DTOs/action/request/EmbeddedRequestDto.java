package com.wora.qatrat7ayat.models.DTOs.action.request;

import com.wora.qatrat7ayat.models.DTOs.action.CreateActionDto;
import com.wora.qatrat7ayat.models.enumes.UrgencyLevel;
import lombok.Getter;

@Getter
public class EmbeddedRequestDto extends CreateActionDto {
    private final Float bloodVolume;
    private final UrgencyLevel urgencyLevel;

    public EmbeddedRequestDto(String message, Long userId, Long hospitalId, Float bloodVolume, UrgencyLevel urgencyLevel) {
        super(message, userId, hospitalId);
        this.bloodVolume = bloodVolume;
        this.urgencyLevel = urgencyLevel;
    }
}
