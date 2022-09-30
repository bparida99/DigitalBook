package com.cts.dbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DigitalBookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBookServerApplication.class, args);
	}

}
