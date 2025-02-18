package com.wora.qatrat7ayat.services.INTER;

import com.wora.qatrat7ayat.models.DTOs.action.donor.CreateDonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.DonorDto;
import com.wora.qatrat7ayat.models.DTOs.action.donor.UpdateDonorDto;
import com.wora.qatrat7ayat.services.GenericService;

public interface IDonorService extends GenericService<CreateDonorDto, UpdateDonorDto, DonorDto, Long> {
}
