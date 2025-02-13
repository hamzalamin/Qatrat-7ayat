package com.wora.qatrat7ayat.models.DTOs.action;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateActionDto {
    private final String message;
    private final Long userId;
    private final Long hospitalId;
}
