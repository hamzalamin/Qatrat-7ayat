package com.wora.qatrat7ayat.security.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}