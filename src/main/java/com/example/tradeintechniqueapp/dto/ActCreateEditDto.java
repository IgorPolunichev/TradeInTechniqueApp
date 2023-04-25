package com.example.tradeintechniqueapp.dto;

import com.example.tradeintechniqueapp.database.entity.ActUser;
import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.database.entity.Work;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.util.List;

@Value
@FieldNameConstants
public class ActCreateEditDto {
    LocalDate date;
    List<Work> works;
    String number;

    List<ActUser> users;

}
