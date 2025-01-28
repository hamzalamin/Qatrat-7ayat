package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.models.DTOs.city.CityDto;
import com.wora.qatrat7ayat.models.DTOs.city.CreateCityDto;
import com.wora.qatrat7ayat.models.DTOs.city.UpdateCityDto;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.models.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper extends GenericMapper<City, CityDto> {
    City toEntity(CityDto dto);
    City toEntity(CreateCityDto dto);
    City toEntity(UpdateCityDto dto);
    CityDto toDto(User user);
}
