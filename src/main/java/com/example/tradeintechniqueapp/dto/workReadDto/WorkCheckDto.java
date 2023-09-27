package com.example.tradeintechniqueapp.dto.workReadDto;

import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.dto.actsDto.ActReadDtoForCheckWorks;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Value
public class WorkCheckDto {
    LocalDate workDate;
    LocalTime startWork;
    LocalTime endWork;
    ActReadDtoForCheckWorks actReadDtoForCheckWorks;

}
