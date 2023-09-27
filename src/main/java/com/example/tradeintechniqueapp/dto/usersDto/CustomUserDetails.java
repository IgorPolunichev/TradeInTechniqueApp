package com.example.tradeintechniqueapp.dto.usersDto;

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
    String lastname;
    String firstname;
    String surname;

    public CustomUserDetails(Long id
            , Integer counterActs
            , String username
            , String password
            , String lastname
            , String firstname
            , String surname
            , Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.counterActs = counterActs;
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.surname = surname;
    }
}
