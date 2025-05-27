package com.wora.qatrat7ayat.services.impl;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.mappers.CityMapper;
import com.wora.qatrat7ayat.models.DTOs.city.CityDto;
import com.wora.qatrat7ayat.models.DTOs.city.CreateCityDto;
import com.wora.qatrat7ayat.models.DTOs.city.UpdateCityDto;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.repositories.CityRepository;
import com.wora.qatrat7ayat.services.inter.ICityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService implements ICityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public CityDto create(CreateCityDto createCityDto) {
        return null;
    }

    @Override
    public CityDto findById(Long id) {
        return null;
    }

    @Override
    public CityDto update(UpdateCityDto updateCityDto, Long id) {
        return null;
    }

    @Override
    public List<CityDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return cityRepository.findAll(pageRequest).stream()
                .map(cityMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public City findCityEntity(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City", id));
    }
}
