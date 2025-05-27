package com.wora.qatrat7ayat.services.inter;

import com.wora.qatrat7ayat.models.DTOs.chat.MessageRequestDTO;
import com.wora.qatrat7ayat.models.DTOs.chat.MessageResponseDTO;

import java.security.Principal;
import java.util.List;

public interface IMessageService {
    MessageResponseDTO saveMessage(MessageRequestDTO dto, Principal principal);
    List<MessageResponseDTO> getMessagesBetweenUsers(String senderEmail, Long receiverId);
}
