package com.testProject.twitchBot.services;

import com.testProject.twitchBot.irc.IRCConnection;
import com.testProject.twitchBot.property.PropertyHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;

@Service
public class TwitchServiceImpl implements TwitchService {

    private IRCConnection ircConnection;
    private String twitchOath;
    private String twitchNickname;
    private String prefix;
    private PropertyHolder twitchProperty;
    private boolean isAuthenticated = false;
    @Autowired
    TwitchRequestHandler twitchRequestHandler;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    public TwitchServiceImpl(PropertyHolder twitchProperty) {
        ircConnection = new IRCConnection("irc.chat.twitch.tv", 6667, twitchProperty.getChannelName());
        ircConnection.connect();
        twitchOath = "oauth:" + twitchProperty.getOauthToken();
        twitchNickname = twitchProperty.getBotName();
        prefix = twitchProperty.getPrefix();

    }


    private void authorize() {
        ircConnection.sendMessage("PASS " + twitchOath);
        ircConnection.sendMessage("NICK " + twitchNickname);

        ircConnection
                .getMessageStream()
                .subscribeOn(Schedulers.parallel())
                .subscribe(this::handleMessage);
        establishAuthentication();
    }


    private void establishAuthentication(){
        int counter = 0;
        int attemptCounter= 100;
        while (counter<attemptCounter) {
            logger.info("INSIDE ESTABLISH");
            if (isAuthenticated) {
                logger.info("ESTABLISH TRUE");
                ircConnection.sendJoinMessageToChannel();
                return;
            } else {
                logger.info("ESTABLISH FALSE");
                ++counter;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void start() {
        authorize();
    }

    @Override
    public void closeConnection() {
        ircConnection.closeSocket();
    }

    @Override
    public void handleMessage(String message) {
        if (message.contains("Welcome, GLHF!")) {
            // Authenticated to the server
            this.isAuthenticated = true;
            logger.info("START AUTHENTICATION" + message);
        } else if (message.startsWith("PING")) {
            // Periodic PING command
            logger.info("PING response : . . . " + message);
            this.ircConnection.sendMessage(message.replace("PING", "PONG"));
            logger.info("DONE");
        }else if (message.contains("!age")) {
            logger.info("AGE COMMAND");
            String sendBy = message.split("!")[0];
            sendBy = sendBy.substring(1,sendBy.length());
            System.out.println(sendBy);
            String age = twitchRequestHandler.getAge(sendBy);
            System.out.println("AGE " + age);
            ircConnection.sendMessageToChannel("@" + sendBy + " " + age);
            ircConnection.sendMessageToChannel("@" + sendBy + "okey? " );

        }else{
            // :rotkudnok!rotkudnok@rotkudnok.tmi.twitch.tv PRIVMSG #orkpod :Как же он эксплорит
            String sendBy = message.split("!")[0];
            sendBy = sendBy.substring(1,sendBy.length());
            String clearMessage = message.replaceAll("\\:.*\\:", "");
            logger.info(sendBy + " " +clearMessage);
            //ircConnection.sendMessageToChannel("@" + sendBy + " " + "Alpharius");
        }
    }
}
