package com.lixegas.messenger_bot.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageCreationRequest {
    private final String text;
}
