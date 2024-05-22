package com.example.tradeintechniqueapp.dto.actsDto;

import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.database.entity.audit.ActPay;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineReadDto;
import com.example.tradeintechniqueapp.dto.usersDto.UserReadDto;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.util.List;

@Value
@FieldNameConstants
public class ActReadDto {

    Long id;
    LocalDate date;
    List<Work> works;
    String number;
    String numberApplication;
    MachineReadDto machine;
    List<UserReadDto> users;

    String car;
    String licensePlate;
    String placeOfWork;
    String actDescription;
    ActPay actPay;



}
