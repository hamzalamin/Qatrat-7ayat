package com.wora.qatrat7ayat.models.DTOs.action.donor;

import com.wora.qatrat7ayat.models.DTOs.action.CreateActionDto;
import lombok.Getter;

@Getter
public class EmbeddedDonorDto extends CreateActionDto {
    private final String availabilityPeriod;


    public EmbeddedDonorDto(String message, Long userId, Long hospitalId, String availabilityPeriod) {
        super(message, userId, hospitalId);
        this.availabilityPeriod = availabilityPeriod;
    }
}
