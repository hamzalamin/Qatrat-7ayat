package com.wora.qatrat7ayat.models.DTOs.action.donor;

import com.wora.qatrat7ayat.models.DTOs.action.ActionDto;
import com.wora.qatrat7ayat.models.DTOs.hospital.EmbeddedHospitalDto;
import lombok.Getter;

@Getter
public class EmbeddedDonorDetailsDto extends ActionDto {
    private final String availabilityPeriod;

    public EmbeddedDonorDetailsDto(Long id, String message, EmbeddedHospitalDto hospital, String availabilityPeriod) {
        super(id, message, hospital);
        this.availabilityPeriod = availabilityPeriod;
    }
}
