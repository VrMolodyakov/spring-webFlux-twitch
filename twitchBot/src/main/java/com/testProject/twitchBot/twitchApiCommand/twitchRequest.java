package com.testProject.twitchBot.twitchApiCommand;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Mono;

public interface twitchRequest {
    Mono<JsonNode> execute(String twitchNickname);
}
