package com.wora.qatrat7ayat.models.DTOs.chat;


import com.wora.qatrat7ayat.models.DTOs.user.EmbeddedUserDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MessageResponseDTO(
        Long id,
        EmbeddedUserDto sender,
        EmbeddedUserDto receiver,
        String content,
        LocalDateTime timestamp
) {
}
