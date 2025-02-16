package com.wora.qatrat7ayat.models.DTOs.action;

import com.wora.qatrat7ayat.models.DTOs.hospital.EmbeddedHospitalDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActionDto {
    private final Long id;
    private final String message;
    private final EmbeddedHospitalDto hospital;
}
