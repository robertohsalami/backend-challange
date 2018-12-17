package com.invillia.acme;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.invillia.acme.api.ProvedorRestService;

@SpringBootApplication
public class InvilliaApplication {
	
	@Bean
	ResourceConfig resourceConfig() {
		return new ResourceConfig().register(ProvedorRestService.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(InvilliaApplication.class, args);
	}
	
}