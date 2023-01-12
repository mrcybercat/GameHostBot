package com.team3m.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GameHostBotApp {
	public static void main(String[] args) {
		SpringApplication.run(GameHostBotApp.class, args);
	}
}
