package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.models.DTOs.chat.MessageDTO;
import com.wora.qatrat7ayat.models.entities.Message;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.repositories.MessageRepository;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.repositories.AuthUserRepository;
import com.wora.qatrat7ayat.services.INTER.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {
    private final MessageRepository messageRepository;
    private final AuthUserRepository userRepository;

    @Override
    public Message saveMessage(MessageDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication.getPrincipal() instanceof UserDetails userDetails)) {
            throw new IllegalStateException("Authentication principal is not an instance of UserDetails");
        }

        String email = userDetails.getUsername();

        AuthenticatedUser sender = userRepository.findByEmail(email)
                .orElseThrow(() -> new com.wora.qatrat7ayat.exceptions.EntityNotFoundException("Sender", email));

        AuthenticatedUser receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new com.wora.qatrat7ayat.exceptions.EntityNotFoundException("Receiver", dto.getReceiverId().toString()));

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(dto.getContent());

        return messageRepository.save(message);
    }

}
