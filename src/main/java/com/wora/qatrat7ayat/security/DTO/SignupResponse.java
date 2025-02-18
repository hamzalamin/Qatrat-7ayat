package com.wora.qatrat7ayat.security.DTO;

import com.wora.qatrat7ayat.models.enumes.BloodType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponse {
    @NotBlank
    private String email;
    private String firstName;
    private String lastName;
    private BloodType bloodType;
    private String phone;
}
