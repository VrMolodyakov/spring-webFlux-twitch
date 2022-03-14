package com.testProject.twitchBot.irc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class IRCConnection {
    private final int port;
    private final String host;
    private final String channelName;
    private Socket twitchSocket;
    private BufferedReader socketReader;
    private PrintWriter socketWriter;
    Flux<String> messageStream;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public IRCConnection( String host, int port, String channelName) {
        this.port = port;
        this.host = host;
        this.channelName = channelName;
    }

    public boolean connect(){
        try {
            twitchSocket = new Socket(host,port);
            socketReader = new BufferedReader(new InputStreamReader(twitchSocket.getInputStream()));
            socketWriter = new PrintWriter(twitchSocket.getOutputStream(),true);
            messageStream = Flux.fromStream(socketReader.lines());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isConnected(){
        return twitchSocket.isConnected();
    }

    public Flux<String> getMessageStream(){
        return messageStream;
    }

    public void closeSocket(){
        try {
            twitchSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToChannel(String message){
        this.sendMessage("PRIVMSG #" + channelName + " :" + message);
    }

    public void sendJoinMessageToChannel(){
        logger.info("JOINING...");
        this.sendMessage("JOIN #" + channelName);
    }

    public void sendMessage(String message){
        logger.info("SENDING MESSAGE..." + message);
        socketWriter.println(message);
    }
}
