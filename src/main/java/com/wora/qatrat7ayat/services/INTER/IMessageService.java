package com.wora.qatrat7ayat.services.INTER;

import com.wora.qatrat7ayat.models.DTOs.chat.MessageDTO;
import com.wora.qatrat7ayat.models.entities.Message;

import java.security.Principal;

public interface IMessageService {
    Message saveMessage(MessageDTO dto, Principal principal);
}
