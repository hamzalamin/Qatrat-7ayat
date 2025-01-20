package com.wora.qatrat7ayat.mappers.api;

public interface GenericMapper<ENTITY, DTO> {
    ENTITY toEntity(DTO dto);
    DTO toDto(ENTITY entity);
}
