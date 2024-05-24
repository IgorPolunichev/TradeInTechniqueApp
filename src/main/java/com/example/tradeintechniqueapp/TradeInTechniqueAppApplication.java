package com.example.tradeintechniqueapp;

import com.example.tradeintechniqueapp.database.entity.Act;
import lombok.Builder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TradeInTechniqueAppApplication {


    public static void main(String[] args) {
        Act.ActBuilder builder = Act.builder();
        SpringApplication.run(TradeInTechniqueAppApplication.class, args);

    }

}
