package com.wora.qatrat7ayat.security.DTO.Role;

import jakarta.validation.constraints.NotBlank;

public record CreateRoleDto(
        @NotBlank
        String name
) {
}
