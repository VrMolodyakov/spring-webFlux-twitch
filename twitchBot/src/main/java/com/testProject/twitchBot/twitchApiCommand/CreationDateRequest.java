package com.testProject.twitchBot.twitchApiCommand;

import com.fasterxml.jackson.databind.JsonNode;
import com.testProject.twitchBot.property.TwitchProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CreationDateRequest implements twitchRequest {

    @Autowired
    TwitchProperty twitchProperty;

    @Override
    public Mono<JsonNode> execute(String twitchNickname) {
        WebClient webClient = WebClient
                                        .builder()
                                        .baseUrl(twitchProperty.getTwitchURl())
                                        .build();
        Mono<JsonNode> jsonResponse = webClient
                                        .get()
                                        .uri(uriBuilder ->uriBuilder.path("/helix/users")
                                                                    .queryParam("login",twitchNickname)
                                                                    .queryParam("api_version",5)
                                                                    .build())
                                        .header("Authorization","Bearer vh6nhxnnxl5pkdafy6ujm0j2verpz9")
                                        .header("Client-ID", twitchProperty.getClientID())
                                        .header("Accept", "application/vnd.twitchtv.v5+json")

                                        .retrieve()
                                        .bodyToMono(JsonNode.class);
        return jsonResponse;


        //+ "users?login=" + twitchNickname




       /* WebClient webClient = WebClient
                .builder()
                .baseUrl(twitchProperty.getTwitchURl() + "users?login=" + twitchNickname)
                .build();
        Mono<String> jsonResponse = webClient
                .get()
                .header("Accept", "application/vnd.twitchtv.v5+json")
                .header("Client-ID", twitchProperty.getClientID())
                .retrieve()
                .bodyToMono(String.class);
        return jsonResponse;*/
    }


}
