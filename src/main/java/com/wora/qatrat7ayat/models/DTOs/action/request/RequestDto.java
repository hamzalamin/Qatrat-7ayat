package com.wora.qatrat7ayat.models.DTOs.action.request;

import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import lombok.Getter;

@Getter
public class RequestDto {
    private final ProfileDto profile;
    private final EmbeddedRequestDto request;

    public RequestDto(ProfileDto profile, EmbeddedRequestDto request) {
        this.profile = profile;
        this.request = request;
    }
}
