package com.wora.qatrat7ayat.models.DTOs.Role;

import jakarta.validation.constraints.NotBlank;

public record EmbeddedRoleDto(
        @NotBlank String name
) {
}
