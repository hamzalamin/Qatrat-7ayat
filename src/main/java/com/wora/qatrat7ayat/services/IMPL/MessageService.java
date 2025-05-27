package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.models.DTOs.chat.MessageRequestDTO;
import com.wora.qatrat7ayat.models.DTOs.chat.MessageResponseDTO;
import com.wora.qatrat7ayat.models.DTOs.user.EmbeddedUserDto;
import com.wora.qatrat7ayat.models.entities.Message;
import com.wora.qatrat7ayat.repositories.MessageRepository;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.services.IAuthService;
import com.wora.qatrat7ayat.services.INTER.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {
    private final MessageRepository messageRepository;
    private final IAuthService authService;

    public MessageResponseDTO saveMessage(MessageRequestDTO dto, Principal principal) {
        String email = principal.getName();
        AuthenticatedUser sender = authService.getUserByEmail(email);
        AuthenticatedUser receiver = authService.getUserById(dto.receiverId());

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(dto.content());

        Message savedMessage = messageRepository.save(message);

        return convertToDTO(savedMessage);
    }

    public List<MessageResponseDTO> getMessagesBetweenUsers(String senderEmail, Long receiverId) {
        AuthenticatedUser sender = authService.getUserByEmail(senderEmail);
        AuthenticatedUser receiver = authService.getUserById(receiverId);

        List<Message> messages = messageRepository.findMessagesBetween(sender.getEmail(), receiver.getId());

        return messages.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private MessageResponseDTO convertToDTO(Message message) {
        EmbeddedUserDto senderProfile = EmbeddedUserDto.builder()
                .id(message.getSender().getId())
                .email(message.getSender().getEmail())
                .firstName(message.getSender().getFirstName())
                .lastName(message.getSender().getLastName())
                .build();

        EmbeddedUserDto receiverProfile = EmbeddedUserDto.builder()
                .id(message.getReceiver().getId())
                .email(message.getReceiver().getEmail())
                .firstName(message.getReceiver().getFirstName())
                .lastName(message.getReceiver().getLastName())
                .build();

        return MessageResponseDTO.builder()
                .id(message.getId())
                .sender(senderProfile)
                .receiver(receiverProfile)
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .build();
    }


}
