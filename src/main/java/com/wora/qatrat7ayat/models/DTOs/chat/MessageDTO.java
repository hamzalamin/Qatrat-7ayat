package com.wora.qatrat7ayat.models.DTOs.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private Long receiverId;
    private String content;
}
