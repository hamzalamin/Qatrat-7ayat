package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.models.entities.Hospital;
import com.wora.qatrat7ayat.repositories.HospitalRepository;
import com.wora.qatrat7ayat.services.INTER.IHospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HospitalService implements IHospitalService {
    private final HospitalRepository hospitalRepository;

    @Override
    public Hospital findHospitalEntity(Long id) {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hospital", id ));
    }
}
