package com.example.tradeintechniqueapp.config;


import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Value
@ConfigurationProperties(prefix = "spring.datasource")
public class DataBaseProperties {
    String url;
    String username;
    String password;
    String driverclassname;

}
