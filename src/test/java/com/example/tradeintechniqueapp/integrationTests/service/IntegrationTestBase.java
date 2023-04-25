package com.example.tradeintechniqueapp.integrationTests.service;

import com.example.tradeintechniqueapp.integrationTests.annotation.IT;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.shaded.org.bouncycastle.util.encoders.UTF8;

import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

@IT
//@Sql(scripts = {
//        "classpath:sql/data.sql"
//})

@WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN", "USER"})
public abstract class IntegrationTestBase {
    public static final MySQLContainer<?> CONTAINER = new MySQLContainer<>("mysql:5");
    @BeforeAll
    static void runContainer(){
        CONTAINER.start();
    }

    @DynamicPropertySource
    static void mySqlProperties(DynamicPropertyRegistry registry) {
        String s = CONTAINER.getJdbcUrl();
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);

    }


}
