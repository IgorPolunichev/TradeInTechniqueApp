package com.example.tradeintechniqueapp.mapper.userMappers;

import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.dto.usersDto.UserReadDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto > {
    @Override
    public  UserReadDto map(User object) {
        return new UserReadDto(object.getId(),
                object.getUsername(),
                object.getFirstname(),
                object.getLastname(),
                object.getSurname(),
                object.getBirthDate(),
                object.getRole(),
                object.getPosition());
    }
}
