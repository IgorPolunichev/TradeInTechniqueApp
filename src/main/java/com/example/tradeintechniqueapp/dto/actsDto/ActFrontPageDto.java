package com.example.tradeintechniqueapp.dto.actsDto;

import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.database.entity.audit.ActPay;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineReadDto;
import com.example.tradeintechniqueapp.dto.usersDto.UserReadDto;
import lombok.Value;

import java.time.LocalDate;


/* Добавление многострочного комментария.
*1
*2
* */

@Value
public class ActFrontPageDto {
    Long id;
    LocalDate date;
    String number;
    String machineSerialNumber;
    String machineType;
    String actDescription;


}
