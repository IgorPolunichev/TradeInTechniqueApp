package com.example.tradeintechniqueapp.config;

import com.example.tradeintechniqueapp.config.castomSuccessHandler.CastomSuccessHandler;
import com.example.tradeintechniqueapp.database.entity.Role;
import com.example.tradeintechniqueapp.database.repository.UserRepository;
import com.example.tradeintechniqueapp.service.UserService;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

   @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
       HttpSecurity users = httpSecurity
//               .csrf().disable()
               .authorizeHttpRequests(urlConfig -> urlConfig
                       .requestMatchers("/login", "/users/registration").permitAll()
                       .requestMatchers("/admin/**").hasAuthority(Role.ADMIN.getAuthority())
                       .anyRequest().authenticated())
               .logout(logout-> logout
                       .logoutUrl("/logout")
                       .logoutSuccessUrl("/login")
                       .deleteCookies("JSESSIONID"))
               .formLogin(login-> login
                       .loginPage("/login")
                       .successHandler(getSuccessHandler())
//                       .defaultSuccessUrl("/users", true)

               );
       return users.build();
   }

   @Bean
    public PasswordEncoder passwordEncoder(){
       return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   }

   public AuthenticationSuccessHandler getSuccessHandler(){
       return new CastomSuccessHandler();
   }



}
