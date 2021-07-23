package com.gtsp.gtsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GtspApplication {

	public static void main(String[] args) {
		SpringApplication.run(GtspApplication.class, args);
	}

}
