package com.wora.qatrat7ayat.models.DTOs.account;

import com.wora.qatrat7ayat.models.enumes.BloodType;
import com.wora.qatrat7ayat.security.models.enume.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserAccountDto {
    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    private String lastName;
    private Long roleId;
    private BloodType bloodType;
    private String phone;
    private Long cityId;
    private Gender gender;
}
