package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.models.DTOs.action.request.EmbeddedRequestDto;
import com.wora.qatrat7ayat.models.entities.Request;
import org.mapstruct.Named;

public class RequestMapperUtils {
    @Named("toEmbeddedRequestDto")
    public static EmbeddedRequestDto toEmbeddedRequestDto(Request request) {
        if (request == null) return null;

        return new EmbeddedRequestDto(
                request.getMessage(),
                request.getUser() != null ? request.getUser().getId() : null,
                request.getHospital() != null ? request.getHospital().getId() : null,
                request.getBloodVolume(),
                request.getUrgencyLevel()
        );
    }
}
