package com.cloud_getway.cloud.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudGetwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGetwayApplication.class, args);
	}

}
