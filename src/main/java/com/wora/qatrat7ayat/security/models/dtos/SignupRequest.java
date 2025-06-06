package com.wora.qatrat7ayat.security.models.dtos;

import com.wora.qatrat7ayat.models.enumes.BloodType;
import com.wora.qatrat7ayat.security.models.enumes.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String firstName;
    private String lastName;
    private BloodType bloodType;
    private String phone;
    private Long cityId;
    private Gender gender;
}
