package com.wora.qatrat7ayat.security.DTO;

import com.wora.qatrat7ayat.models.DTOs.city.EmbeddedCityDto;
import com.wora.qatrat7ayat.models.enumes.BloodType;
import com.wora.qatrat7ayat.security.DTO.Role.EmbeddedRoleDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponse {
    private Long id;
    @NotBlank
    private String email;
    private String firstName;
    private String lastName;
    private BloodType bloodType;
    private String phone;
    private EmbeddedCityDto city;
    private EmbeddedRoleDto role;
}
