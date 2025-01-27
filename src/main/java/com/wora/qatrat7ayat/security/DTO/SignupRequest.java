package com.wora.qatrat7ayat.security.DTO;

import com.wora.qatrat7ayat.models.enumes.BloodType;
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

    private BloodType bloodType;

    private String firstName;

    private String lastName;

    private String phone;

    private String availabilityMessage;

    private Long cityId;

    private Long roleId;
}
