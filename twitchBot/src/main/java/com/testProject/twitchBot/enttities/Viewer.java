package com.testProject.twitchBot.enttities;

import lombok.Builder;

import java.util.List;
@Builder
public class Viewer {
    private List<String> messages;
    private String viewerNickname;

}
