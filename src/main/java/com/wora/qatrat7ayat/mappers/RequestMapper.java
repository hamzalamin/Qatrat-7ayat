package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.models.DTOs.action.request.CreateRequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.EmbeddedRequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.RequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.UpdateRequestDto;
import com.wora.qatrat7ayat.models.entities.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = RequestMapperUtils.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RequestMapper extends GenericMapper<Request, RequestDto> {

    Request toEntity(RequestDto dto);
    Request toEntity(UpdateRequestDto dto);
    Request toEntity(CreateRequestDto dto);
    Request toEntity(EmbeddedRequestDto dto);

    @Mapping(target = "profile", source = "user")
    @Mapping(target = "request", source = "request", qualifiedByName = "toEmbeddedRequestDto")
    RequestDto toDto(Request request);
}