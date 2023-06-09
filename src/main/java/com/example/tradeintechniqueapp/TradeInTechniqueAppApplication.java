package com.example.tradeintechniqueapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TradeInTechniqueAppApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(TradeInTechniqueAppApplication.class, args);

	}

}
