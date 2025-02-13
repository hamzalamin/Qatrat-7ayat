package com.wora.qatrat7ayat.models.DTOs.action.request;

import com.wora.qatrat7ayat.models.DTOs.action.CreateActionDto;
import com.wora.qatrat7ayat.models.enumes.BloodType;
import com.wora.qatrat7ayat.models.enumes.UrgencyLevel;
import lombok.Getter;

@Getter
public class EmbeddedRequestDto extends CreateActionDto {
    private final Float bloodVolume;
    private final UrgencyLevel urgencyLevel;

    public EmbeddedRequestDto(String message, Long userId, Long hospitalId, String firstName, String lastName, String psudoName, String phone, BloodType bloodType, Long city, String message1, Long hospital, Long user, Float bloodVolume, UrgencyLevel urgencyLevel) {
        super(message, userId, hospitalId);
        this.bloodVolume = bloodVolume;
        this.urgencyLevel = urgencyLevel;
    }
}
