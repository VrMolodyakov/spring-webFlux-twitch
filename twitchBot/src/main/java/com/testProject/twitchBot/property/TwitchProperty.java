package com.testProject.twitchBot.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TwitchProperty {
    private String twitchURl = "https://api.twitch.tv";
    private String clientID = "gp762nuuoqcoxypju8c569th9wz7q5";

    //"https://api.twitch.tv/kraken/
}
