package com.example.tradeintechniqueapp.config;

import com.example.tradeintechniqueapp.config.condition.JpaCondition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Conditional(JpaCondition.class)
@Configuration
public class JpaConfiguration {

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataBaseProperties dataBaseProperties(){
//        return new DataBaseProperties();
//    }
}
