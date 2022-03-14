package com.testProject.twitchBot.services;

import org.springframework.stereotype.Service;


public interface TwitchService {
    void start();
    void closeConnection();
    void handleMessage(String message);

}
