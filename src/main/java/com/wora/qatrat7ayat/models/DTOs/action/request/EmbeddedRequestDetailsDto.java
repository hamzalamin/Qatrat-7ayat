package com.wora.qatrat7ayat.models.DTOs.action.request;

import com.wora.qatrat7ayat.models.DTOs.action.ActionDto;
import com.wora.qatrat7ayat.models.DTOs.hospital.EmbeddedHospitalDto;
import com.wora.qatrat7ayat.models.enumes.UrgencyLevel;
import lombok.Getter;

@Getter
public class EmbeddedRequestDetailsDto extends ActionDto {
    private final Float bloodVolume;
    private final UrgencyLevel urgencyLevel;


    public EmbeddedRequestDetailsDto(Long id, String message, EmbeddedHospitalDto hospital, Float bloodVolume, UrgencyLevel urgencyLevel) {
        super(id, message, hospital);
        this.bloodVolume = bloodVolume;
        this.urgencyLevel = urgencyLevel;
    }
}
