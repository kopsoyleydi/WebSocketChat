package com.example.strangerChat.api.ws;


import com.example.strangerChat.api.domains.Chat;
import com.example.strangerChat.api.dto.ChatDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class ChatWsController {

    SimpMessagingTemplate simpMessagingTemplate;

    private static final String CREATE_CHAT = "/topic.chat.{chat_name}.create";

    private static final String CREATE_CHAT_EVENT = "/topic.chat.create.event";

    @MessageMapping(CREATE_CHAT)
    public void createChat(@DestinationVariable("chat_name") String chatName){
        Chat chat = Chat.builder()
                .name(chatName)
                .build();

        simpMessagingTemplate.convertAndSend(
                CREATE_CHAT_EVENT, ChatDTO.builder()
                        .id(chat.getId())
                        .name(chatName)
                        .createdAt(chat.getCreatedAt())
                        .build()
        );
    }

    @SubscribeMapping(CREATE_CHAT_EVENT)
    public ChatDTO fetchCreateChatEvent(){
        return null;
    }
}
