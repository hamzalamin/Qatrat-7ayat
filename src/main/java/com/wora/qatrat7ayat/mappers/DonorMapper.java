package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.models.DTOs.action.donor.CreateDonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.DonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.UpdateDonorDto;
import com.wora.qatrat7ayat.models.entities.Donor;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface DonorMapper extends GenericMapper<Donor, DonorDto> {
    Donor toEntity(DonorDto dto);
    Donor toEntity(CreateDonorDto dto);
    Donor toEntity(UpdateDonorDto dto);
    DonorDto toDto(Donor action);
}
