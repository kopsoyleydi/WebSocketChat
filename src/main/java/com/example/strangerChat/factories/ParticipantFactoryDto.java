package com.example.strangerChat.factories;


import com.example.strangerChat.api.domains.Participant;
import com.example.strangerChat.api.dto.ParticipantDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ParticipantFactoryDto {

    public ParticipantDTO makeParticipantDto(Participant participant) {
        return ParticipantDTO.builder()
                .id(participant.getId())
                .enterAt(participant.getEnterAt())
                .build();
    }
}