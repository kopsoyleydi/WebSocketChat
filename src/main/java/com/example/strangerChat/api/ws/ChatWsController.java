package com.example.strangerChat.api.ws;


import com.example.strangerChat.api.domains.Chat;
import com.example.strangerChat.api.dto.ChatDTO;
import com.example.strangerChat.api.dto.MessageDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class ChatWsController {

    SimpMessagingTemplate simpMessagingTemplate;

    private static final String CREATE_CHAT = "/topic.chat.create";

    private static final String FETCH_CREATE_CHAT_EVENT = "/topic.chat.create.event";

    private static final String SEND_MESSAGES_TO_ALL = "/topic/chat.{chat_id}.messages";

    private static final String SEND_MESSAGES_TO_PARTICIPANT = "/topic/chat.{chat_id}.messages";

    private static final String FETCH_MESSAGES = "/topic/chat.{chat_id}.messages";

    private static final String FETCH_PERSONAL_MESSAGES = "/topic/chat.{chat_id}.{participants_id}.messages";

    @MessageMapping(CREATE_CHAT)
    public void createChat(String chatName){
        Chat chat = Chat.builder()
                .name(chatName)
                .build();

        simpMessagingTemplate.convertAndSend(
                FETCH_CREATE_CHAT_EVENT, ChatDTO.builder()
                        .id(chat.getId())
                        .name(chatName)
                        .createdAt(chat.getCreatedAt())
                        .build()
        );
    }

    @SubscribeMapping(FETCH_CREATE_CHAT_EVENT)
    public ChatDTO fetchCreateChatEvent(){
        return null;
    }

    @MessageMapping(SEND_MESSAGES_TO_ALL)
    public void sendMessageToAll(
            @DestinationVariable("chat_id") String chatId,
            String message,
            @Header String sessionId){
        sendMessage(
                getFetchMessagesDestination(chatId),
                sessionId,
                message
        );
    }

    @MessageMapping(SEND_MESSAGES_TO_PARTICIPANT)
    public void sendMessageToParticipant(
            @DestinationVariable("chat_id") String chatId,
            @DestinationVariable("participant_id") String participantId,
            String message,
            @Header String sessionId){
        sendMessage(
                getFetchPersonalMessagesDestination(chatId, participantId),
                sessionId,
                message
        );
    }

    private void sendMessage(String destination, String sessionId, String message){
        simpMessagingTemplate.convertAndSend(
                destination,
                MessageDTO.builder()
                        .from(sessionId)
                        .message(message)
                        .build()
        );
    }

    @SubscribeMapping(FETCH_MESSAGES)
    public MessageDTO fetchMessages(){
        return null;
    }

    @SubscribeMapping(FETCH_PERSONAL_MESSAGES)
    public MessageDTO fetchPersonalMessages(){
        return null;
    }

    public String getFetchMessagesDestination(String chatId){
        return FETCH_MESSAGES.replace("{chat_id}", chatId);
    }

    public String getFetchPersonalMessagesDestination(String chatId, String participantId){
        return FETCH_PERSONAL_MESSAGES
                .replace("{chat_id}", chatId)
                .replace("{participant_id}", participantId);
    }



}
