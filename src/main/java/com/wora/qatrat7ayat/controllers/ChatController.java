package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.models.DTOs.chat.MessageDTO;
import com.wora.qatrat7ayat.models.entities.Message;
import com.wora.qatrat7ayat.services.INTER.IMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChatController {

    private final IMessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/chat")
    public void processMessage(@RequestBody @Valid MessageDTO message) {
        Message saved = messageService.saveMessage(message);

        messagingTemplate.convertAndSendToUser(
                String.valueOf(message.getReceiverId()),
                "/queue/messages",
                message
        );
    }
}

