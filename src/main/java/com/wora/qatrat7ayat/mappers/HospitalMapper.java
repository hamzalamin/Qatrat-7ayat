package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.models.DTOs.hospital.HospitalDto;
import com.wora.qatrat7ayat.models.DTOs.hospital.CreateHospitalDto;
import com.wora.qatrat7ayat.models.DTOs.hospital.UpdateHospitalDto;
import com.wora.qatrat7ayat.models.entities.Hospital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HospitalMapper extends GenericMapper<Hospital, HospitalDto> {
    Hospital toEntity(HospitalDto dto);
    Hospital toEntity(CreateHospitalDto dto);
    Hospital toEntity(UpdateHospitalDto dto);
    HospitalDto toDto(Hospital hospital);
}
