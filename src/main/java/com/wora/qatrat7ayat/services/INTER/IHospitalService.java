package com.wora.qatrat7ayat.services.INTER;

import com.wora.qatrat7ayat.models.DTOs.hospital.CreateHospitalDto;
import com.wora.qatrat7ayat.models.DTOs.hospital.HospitalDto;
import com.wora.qatrat7ayat.models.DTOs.hospital.UpdateHospitalDto;
import com.wora.qatrat7ayat.models.entities.Hospital;
import com.wora.qatrat7ayat.services.GenericService;

public interface IHospitalService extends GenericService<CreateHospitalDto, UpdateHospitalDto, HospitalDto, Long> {
    Hospital findHospitalEntity(Long id);
}
