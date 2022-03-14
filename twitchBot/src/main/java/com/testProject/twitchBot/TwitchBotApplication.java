package com.testProject.twitchBot;

import com.testProject.twitchBot.services.TwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitchBotApplication implements CommandLineRunner {

	@Autowired
	TwitchService twitchService;
	//3j4o631w8x4idq2e82ldv62118s6e1
	//vh6nhxnnxl5pkdafy6ujm0j2verpz9
	public static void main(String[] args) {
		SpringApplication.run(TwitchBotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		twitchService.start();
	}
}
