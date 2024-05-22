package com.example.tradeintechniqueapp.dto.actsDto;
import lombok.Value;
import java.time.LocalDate;
/*
* Добавили строку
* Добавили еще одну.
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
