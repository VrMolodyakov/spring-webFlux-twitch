package com.testProject.twitchBot.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PropertyHolder {
    @Value("${twitch.botName}")
    private String botName;

    @Value("${twitch.channelName}")
    private String channelName;

    @Value("${twitch.prefix}")
    private String prefix;

    @Value("${twitch.oauthToken}")
    private String oauthToken;
}
