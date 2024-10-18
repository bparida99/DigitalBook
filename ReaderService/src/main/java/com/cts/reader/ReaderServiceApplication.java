package com.cts.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ReaderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReaderServiceApplication.class, args);
	}

}
