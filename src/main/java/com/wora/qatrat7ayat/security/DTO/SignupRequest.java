package com.wora.qatrat7ayat.security.DTO;

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

    private Long roleId;
}
