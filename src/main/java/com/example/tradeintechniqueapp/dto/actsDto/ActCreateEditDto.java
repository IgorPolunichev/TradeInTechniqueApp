package com.example.tradeintechniqueapp.dto.actsDto;

import com.example.tradeintechniqueapp.database.entity.ActUser;
import com.example.tradeintechniqueapp.database.entity.Machine;
import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.database.entity.audit.ActPay;
import com.example.tradeintechniqueapp.dto.PartsDto.PartReadDto;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineReadDto;
import com.example.tradeintechniqueapp.dto.usersDto.UserReadDto;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
//@FieldNameConstants
public class ActCreateEditDto {
    Long id;
    LocalDate date;
    List<Work> works;
    String number;
    String numberApplication;
    MachineReadDto machine;
    List<UserReadDto> users;
    List<PartReadDto> parts;
    String actDescription;
    String car;
    String licensePlate;
    String placeOfWork;
    ActPay actPay;




}

