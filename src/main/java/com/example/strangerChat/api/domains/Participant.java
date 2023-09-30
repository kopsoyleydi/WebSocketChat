package com.example.strangerChat.api.domains;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Participant implements Serializable {

    String sessionId;

    String participantId;

    @Builder.Default
    Instant enterAt = Instant.now();

}
