package com.wora.qatrat7ayat.models.DTOs.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateRoleDto(
        @NotBlank String name
) {
}
