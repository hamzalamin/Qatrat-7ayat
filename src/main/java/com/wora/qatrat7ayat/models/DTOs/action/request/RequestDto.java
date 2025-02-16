package com.wora.qatrat7ayat.models.DTOs.action.request;

import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import lombok.Getter;

@Getter
public class RequestDto {
    private final ProfileDto profile;
    private final EmbeddedRequestDetailsDto request;

    public RequestDto(ProfileDto profile, EmbeddedRequestDetailsDto request) {
        this.profile = profile;
        this.request = request;
    }
}
