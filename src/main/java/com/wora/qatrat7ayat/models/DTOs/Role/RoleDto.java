package com.wora.qatrat7ayat.models.DTOs.Role;

import com.wora.qatrat7ayat.models.DTOs.User.EmbeddedUserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RoleDto(
        @NotNull Long id,
        @NotBlank String name,
        @NotNull List<EmbeddedUserDto> users
) {
}
