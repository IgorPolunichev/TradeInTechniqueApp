package com.example.tradeintechniqueapp.mapper.userMappers;

import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.dto.usersDto.UserCreateEditDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateEditDto object){
        User user = new User();
        copy(object, user);
        return user;
    }

    private void copy(UserCreateEditDto object, User user) {
        user.setUsername(object.getUsername());
        user.setLastname(object.getLastname());
        user.setRole(object.getRole());
        user.setFirstname(object.getFirstname());
        user.setPosition(object.getPosition());
        user.setSurname(object.getSurname());
        user.setBirthDate(object.getBirthDate());

        Optional.ofNullable(object.getRowPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
    }
}
