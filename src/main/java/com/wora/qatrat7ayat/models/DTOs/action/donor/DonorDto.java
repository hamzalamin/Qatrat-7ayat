package com.wora.qatrat7ayat.models.DTOs.action.donor;


import com.wora.qatrat7ayat.models.DTOs.user.ProfileDto;
import lombok.Getter;

@Getter
public class DonorDto{
    private final ProfileDto profile;
    private final EmbeddedDonorDetailsDto donor;

    public DonorDto(ProfileDto profile, EmbeddedDonorDetailsDto donor) {
        this.profile = profile;
        this.donor = donor;
    }
}
