package com.wora.qatrat7ayat.models.DTOs.action.donor;


public record DonorDto(
        String message,
        Long hospitalId,
        String availabilityPeriod,
        String firstName,
        String lastName,
        String bloodType,
        String phone
) {
}
