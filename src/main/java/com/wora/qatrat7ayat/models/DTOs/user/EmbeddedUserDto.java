package com.wora.qatrat7ayat.models.DTOs.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmbeddedUserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
}
