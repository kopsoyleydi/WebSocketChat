package com.example.strangerChat.api.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatDTO {

    String id;

    String name;

    Instant createdAt;
}
