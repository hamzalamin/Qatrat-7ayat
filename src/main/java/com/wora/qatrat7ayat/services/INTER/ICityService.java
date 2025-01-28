package com.wora.qatrat7ayat.services.INTER;

import com.wora.qatrat7ayat.models.DTOs.city.CityDto;
import com.wora.qatrat7ayat.models.DTOs.city.CreateCityDto;
import com.wora.qatrat7ayat.models.DTOs.city.UpdateCityDto;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.services.GenericService;

public interface ICityService extends GenericService<CreateCityDto, UpdateCityDto, CityDto, Long> {
    City findCityEntity(Long id);
}
