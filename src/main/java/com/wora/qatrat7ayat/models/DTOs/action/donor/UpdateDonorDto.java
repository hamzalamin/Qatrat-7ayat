package com.wora.qatrat7ayat.models.DTOs.action.donor;


import com.wora.qatrat7ayat.models.DTOs.user.CreateProfileDto;
import lombok.Getter;

@Getter
public class UpdateDonorDto{
    private final CreateProfileDto profile;
    private final EmbeddedDonorDto donor;

    public UpdateDonorDto(CreateProfileDto profile, EmbeddedDonorDto donor) {
        this.profile = profile;
        this.donor = donor;
    }
}
