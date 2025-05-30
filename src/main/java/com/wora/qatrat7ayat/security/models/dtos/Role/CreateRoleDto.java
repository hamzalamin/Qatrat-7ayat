package com.wora.qatrat7ayat.security.models.dtos.Role;

import jakarta.validation.constraints.NotBlank;

public record CreateRoleDto(
        @NotBlank
        String name
) {
}
