package com.altrocks.river;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReverRaiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReverRaiderApplication.class, args);
	}

}
