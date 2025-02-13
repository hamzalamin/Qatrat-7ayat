package com.wora.qatrat7ayat.models.DTOs.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class UpdateActionDto {
    private final String message;
    private final Long userId;
    private final Long hospitalId;
}
