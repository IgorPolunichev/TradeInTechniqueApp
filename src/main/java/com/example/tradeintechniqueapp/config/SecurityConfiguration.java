package com.example.tradeintechniqueapp.config;

import com.example.tradeintechniqueapp.config.castomSuccessHandler.CastomSuccessHandler;
import com.example.tradeintechniqueapp.database.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        HttpSecurity users = httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(urlConfig -> urlConfig
                        .requestMatchers("/login"
                                , "/api/v1/**"
                                , "/v3/api-docs/**"
                                , "/swagger-ui/**"
                                , "/swagger-ui.html"
                        , "/v3/api-docs/**"
                                , "/swagger-ui/**").permitAll()
                        .requestMatchers("/users", "/api/v1/**", "api/v3/companies/**").hasAuthority(Role.ADMIN.getAuthority())
                        .anyRequest().authenticated())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID"))
                .formLogin(login -> login
                                .loginPage("/login")
                                .successHandler(getSuccessHandler())
//                       .defaultSuccessUrl("/users")

                );
        return users.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public AuthenticationSuccessHandler getSuccessHandler() {
        return new CastomSuccessHandler();
    }


}
