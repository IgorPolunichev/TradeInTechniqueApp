package com.example.tradeintechniqueapp.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Value
@EqualsAndHashCode(callSuper = false)
public class CustomUserDetails extends User {
    Long id;
    Integer counterActs;

    public CustomUserDetails(Long id
            , Integer counterActs
            , String username
            , String password
            , Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.counterActs = counterActs;
        this.id = id;
    }
}
