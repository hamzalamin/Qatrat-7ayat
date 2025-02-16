package com.wora.qatrat7ayat.models.DTOs.hospital;

import com.wora.qatrat7ayat.models.DTOs.action.ActionDto;

import java.util.List;

public record HospitalDto(
        Long id,
        String name,
        List<ActionDto> actions
) {
}
