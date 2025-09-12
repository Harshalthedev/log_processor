package com.harshal.logs.log_processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // 👈 this enables @Scheduled tasks

public class LogProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogProcessorApplication.class, args);
	}

}
