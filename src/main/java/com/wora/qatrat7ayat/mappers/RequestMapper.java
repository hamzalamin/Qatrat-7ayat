package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.models.DTOs.action.request.CreateRequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.RequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.UpdateRequestDto;
import com.wora.qatrat7ayat.models.entities.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestMapper extends GenericMapper<Request, RequestDto> {
    Request toEntity(RequestDto dto);
    Request toEntity(UpdateRequestDto dto);
    Request toEntity(CreateRequestDto dto);
    @Mapping(source = "hospital.id", target = "request.hospitalId")
    RequestDto toDto(Request request);
}
