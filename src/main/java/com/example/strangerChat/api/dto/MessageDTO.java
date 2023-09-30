package com.example.strangerChat.api.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageDTO {

    String from;

    String message;

    @Builder.Default
    Instant createdAt = Instant.now();
}
