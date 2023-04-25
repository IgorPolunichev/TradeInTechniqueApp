package com.example.tradeintechniqueapp.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Map;
@Value
@ConfigurationProperties(prefix = "spring.datasource")
public class DataBaseProperties {
    String url;
    String username;
    String password;
    String driverclassname;

}
