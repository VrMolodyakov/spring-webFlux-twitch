package com.testProject.twitchBot.eventListeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ConfigIsDoneEvent {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @EventListener(ApplicationStartedEvent.class)
    public void init(){
        log.info("CONFIGURATION IS DONE");

    }
}
