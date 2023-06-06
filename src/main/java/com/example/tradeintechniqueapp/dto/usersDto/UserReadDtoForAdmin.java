package com.example.tradeintechniqueapp.dto.usersDto;

import com.example.tradeintechniqueapp.database.entity.Position;
import com.example.tradeintechniqueapp.database.entity.Role;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;

@Value
public class UserReadDtoForAdmin {
    Long id;
    String userName;
    String firstname;
    String lastname;
    String surname;
    LocalDate birthDate;
    Role role;
    Position position;
    Instant createdAt;
    Instant modifiedAt;
    String createdBy;
    String LastModifiedBy;

}
