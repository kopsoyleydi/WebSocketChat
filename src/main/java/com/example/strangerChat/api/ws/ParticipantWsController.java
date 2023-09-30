package com.example.strangerChat.api.ws;


import com.example.strangerChat.api.dto.ChatDTO;
import com.example.strangerChat.api.dto.MessageDTO;
import com.example.strangerChat.api.dto.ParticipantDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ParticipantWsController {

    SimpMessagingTemplate simpMessagingTemplate;


    private static final String FETCH_PARTICIPANT_JOIN_IN_CHAT = "/topic/chats.{chat_id}.participants.join";
    private static final String FETCH_PARTICIPANT_LEAVE_FROM_CHAT = "/topic/chats.{chat_id}.participants.leave";

    @SubscribeMapping(FETCH_PARTICIPANT_JOIN_IN_CHAT)
    public ParticipantDTO fetchParticipantJoinInChat(){
        return null;
    }

    @SubscribeMapping(FETCH_PARTICIPANT_LEAVE_FROM_CHAT)
    public ParticipantDTO fetchParticipantLeaveFromChat(){
        return null;
    }

    public static String getFetchParticipantJoinInChatDestination(String chatId){
        return FETCH_PARTICIPANT_JOIN_IN_CHAT.replace("chat_id", chatId);
    }

    public static String getFetchParticipantLeaveFromChatDestination(String chatId){
        return FETCH_PARTICIPANT_LEAVE_FROM_CHAT.replace("chat_id", chatId);
    }

}
