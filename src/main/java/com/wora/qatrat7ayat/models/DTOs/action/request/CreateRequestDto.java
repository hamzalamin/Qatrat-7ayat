package com.wora.qatrat7ayat.models.DTOs.action.request;

import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import lombok.Getter;

@Getter
public class CreateRequestDto {
    private final CreateProfileDto profile;
    private final EmbeddedRequestDto request;

    public CreateRequestDto(CreateProfileDto profile, EmbeddedRequestDto request) {
        this.profile = profile;
        this.request = request;
    }
}
