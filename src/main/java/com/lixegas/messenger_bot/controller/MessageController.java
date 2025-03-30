package com.lixegas.messenger_bot.controller;

import com.lixegas.messenger_bot.model.request.MessageCreationRequest;
import com.lixegas.messenger_bot.model.response.MessageCreationResponse;
import com.lixegas.messenger_bot.service.TelegramBotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/telegram")
@AllArgsConstructor
public class MessageController {
    private final TelegramBotService telegramBotService;

    @PostMapping("/send")
    public MessageCreationResponse sendMessage(@RequestBody MessageCreationRequest messageRequest) {
        return telegramBotService.sendMessage(messageRequest);
    }
}


