package com.example.strangerChat.factories;


import com.example.strangerChat.api.domains.Chat;
import com.example.strangerChat.api.dto.ChatDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ChatDtoFactory {

    public ChatDTO makeChatDto(Chat chat) {
        return ChatDTO.builder()
                .id(chat.getId())
                .name(chat.getName())
                .createdAt(chat.getCreatedAt())
                .build();
    }
}