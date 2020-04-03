package com.luckycatlabs.sunrisesunset.api.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan

@SpringBootApplication
public class SunmodelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunmodelApplication.class, args);
	}

}
