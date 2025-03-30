package com.lixegas.messenger_bot.service;

import com.lixegas.messenger_bot.model.request.MessageCreationRequest;
import com.lixegas.messenger_bot.model.response.MessageCreationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TelegramBotService {


    //Create the bot on telegram with "botfather"
    @Value("${telegram.botToken}")
    private String botToken;

    //Add the bot to the reference group do /start in the group and then go to this link https://api.telegram.org/bot<TOKENDELBOT>/getUpdates with your bot's token and take the "chatId"
    @Value("${telegram.chatId}")
    private String chatId;

    @Value("${telegram.baseUrl}")
    private String baseUrl;

    //https://docs.spring.io/spring-framework/reference/web/webflux-webclient/client-builder.html documentation

    public WebClient webClientInstance() {
        return WebClient.builder()
                .baseUrl(baseUrl + botToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public MessageCreationResponse sendMessage(MessageCreationRequest messageRequest) {
        String requestBody = "{\"chat_id\":\"" + chatId + "\", \"text\":\"" + messageRequest.getText() + "\"}";

        WebClient client = webClientInstance();

        Mono<String> response = client.post()
                .uri("/sendMessage")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);

        String responseBody = response.block();
        return new MessageCreationResponse("Success");
    }

}
