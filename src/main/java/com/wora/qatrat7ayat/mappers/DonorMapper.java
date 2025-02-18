package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.models.DTOs.action.donor.CreateDonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.DonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.EmbeddedDonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.UpdateDonorDto;
import com.wora.qatrat7ayat.models.entities.Donor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DonorMapper extends GenericMapper<Donor, DonorDto> {
    Donor toEntity(DonorDto dto);
    Donor toEntity(UpdateDonorDto dto);
    Donor toEntity(CreateDonorDto dto);
    Donor toEntity(EmbeddedDonorDto dto);
    @Mapping(target = "profile", source = "user")
    @Mapping(target = "donor", source = "donor")
    DonorDto toDto(Donor donor);
}
