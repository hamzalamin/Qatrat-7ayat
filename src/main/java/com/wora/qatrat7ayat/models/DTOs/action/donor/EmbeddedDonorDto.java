package com.wora.qatrat7ayat.models.DTOs.action.donor;

import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import lombok.Getter;

@Getter
public class EmbeddedDonorDto extends CreateDonorDto {
    private final String availabilityPeriod;


    public EmbeddedDonorDto(CreateProfileDto profile, EmbeddedDonorDto donor, String availabilityPeriod) {
        super(profile, donor);
        this.availabilityPeriod = availabilityPeriod;
    }
}
