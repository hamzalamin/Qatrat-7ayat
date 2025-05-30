package com.wora.qatrat7ayat.models.DTOs.account;

import com.wora.qatrat7ayat.models.enumes.BloodType;
import com.wora.qatrat7ayat.security.models.enumes.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserAccountDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String firstName;
    private String lastName;
    private Long roleId;
    private BloodType bloodType;
    private String phone;
    private Long cityId;
    private Gender gender;
}
