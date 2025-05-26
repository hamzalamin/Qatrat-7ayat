package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.models.DTOs.chat.MessageDTO;
import com.wora.qatrat7ayat.services.INTER.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChatController {

    private final IMessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void receiveWebSocketMessage(MessageDTO message, Principal principal) {
        messageService.saveMessage(message, principal);
        messagingTemplate.convertAndSendToUser(
                String.valueOf(message.receiverId()),
                "/queue/messages",
                message
        );
    }

}

