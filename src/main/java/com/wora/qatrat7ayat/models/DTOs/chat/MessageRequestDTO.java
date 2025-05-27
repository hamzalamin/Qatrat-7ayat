package com.wora.qatrat7ayat.models.DTOs.chat;


import lombok.Builder;

@Builder
public record MessageRequestDTO(
        Long receiverId,
        String content
) {
}
