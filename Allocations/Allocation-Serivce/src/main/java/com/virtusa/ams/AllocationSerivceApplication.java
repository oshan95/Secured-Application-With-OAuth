package com.virtusa.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableEurekaServer
public class AllocationSerivceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllocationSerivceApplication.class, args);
	}

}
