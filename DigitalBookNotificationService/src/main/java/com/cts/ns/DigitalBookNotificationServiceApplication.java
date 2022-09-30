package com.cts.ns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DigitalBookNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBookNotificationServiceApplication.class, args);
	}

}
