package com.wora.qatrat7ayat.services.impl;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.mappers.HospitalMapper;
import com.wora.qatrat7ayat.models.DTOs.hospital.CreateHospitalDto;
import com.wora.qatrat7ayat.models.DTOs.hospital.HospitalDto;
import com.wora.qatrat7ayat.models.DTOs.hospital.UpdateHospitalDto;
import com.wora.qatrat7ayat.models.entities.Hospital;
import com.wora.qatrat7ayat.repositories.HospitalRepository;
import com.wora.qatrat7ayat.services.inter.IHospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HospitalService implements IHospitalService {
    private final HospitalRepository hospitalRepository;
    private final HospitalMapper hospitalMapper;

    @Override
    public Hospital findHospitalEntity(Long id) {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hospital", id ));
    }


    @Override
    public HospitalDto create(CreateHospitalDto createHospitalDto) {
        return null;
    }

    @Override
    public HospitalDto findById(Long id) {
        return null;
    }

    @Override
    public HospitalDto update(UpdateHospitalDto updateHospitalDto, Long id) {
        return null;
    }

    @Override
    public List<HospitalDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return hospitalRepository.findAll(pageRequest).stream()
                .map(hospitalMapper::toDto)
                .toList();    }

    @Override
    public void delete(Long id) {

    }
}
