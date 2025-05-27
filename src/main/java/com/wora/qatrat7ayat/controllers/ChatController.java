package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.models.DTOs.chat.MessageRequestDTO;
import com.wora.qatrat7ayat.models.DTOs.chat.MessageResponseDTO;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.services.IAuthService;
import com.wora.qatrat7ayat.services.inter.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChatController {

    private final IMessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;
    private final IAuthService authService;

    @MessageMapping("/chat")
    public void receiveWebSocketMessage(@Payload MessageRequestDTO message, Principal principal) {
        if (principal == null) {
            messagingTemplate.convertAndSend("/topic/errors",
                    "Authentication required for chat");
            return;
        }

        try {
            MessageResponseDTO savedMessage = messageService.saveMessage(message, principal);

            AuthenticatedUser receiver = authService.getUserById(message.receiverId());
            String receiverEmail = receiver.getEmail();

            messagingTemplate.convertAndSendToUser(
                    receiverEmail,
                    "/queue/messages",
                    savedMessage
            );

            messagingTemplate.convertAndSendToUser(
                    principal.getName(),
                    "/queue/messages",
                    savedMessage
            );

        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();

            if (principal != null) {
                messagingTemplate.convertAndSendToUser(
                        principal.getName(),
                        "/queue/errors",
                        "Failed to send message: " + e.getMessage()
                );
            }
        }
    }

    @GetMapping("/messages/{receiverId}")
    public ResponseEntity<List<MessageResponseDTO>> getChatHistory(
            @PathVariable Long receiverId,
            Principal principal) {
        String senderEmail = principal.getName();
        List<MessageResponseDTO> messages = messageService.getMessagesBetweenUsers(senderEmail, receiverId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}