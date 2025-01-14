package com.wora.qatrat7ayat.security.mappers.api;

public interface GenericMapper<ENTITY, DTO> {
    ENTITY toEntity(ENTITY entity);
    DTO toDto(DTO dto);
}
