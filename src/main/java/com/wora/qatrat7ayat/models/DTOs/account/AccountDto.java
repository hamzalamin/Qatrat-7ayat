package com.wora.qatrat7ayat.models.DTOs.account;

import com.wora.qatrat7ayat.models.DTOs.city.EmbeddedCityDto;
import com.wora.qatrat7ayat.models.enumes.BloodType;
import com.wora.qatrat7ayat.security.DTO.Role.EmbeddedRoleDto;
import com.wora.qatrat7ayat.security.models.enume.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class AccountDto{
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String firstName;
    private String lastName;
    private EmbeddedRoleDto role;
    private BloodType bloodType;
    private String phone;
    private EmbeddedCityDto city;
    private Gender gender;
    Date createdAt;
    Date updatedAt;
}
