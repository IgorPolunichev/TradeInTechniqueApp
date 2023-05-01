package com.example.tradeintechniqueapp.dto;

import com.example.tradeintechniqueapp.database.entity.Position;
import com.example.tradeintechniqueapp.database.entity.Role;
import com.example.tradeintechniqueapp.validation.UserInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo
public class UserCreateEditDto {

    String username;
    String firstname;
    String lastname;
    String surname;
    LocalDate birthDate;
    Role role;
    Position position;
    String rowPassword;



}
