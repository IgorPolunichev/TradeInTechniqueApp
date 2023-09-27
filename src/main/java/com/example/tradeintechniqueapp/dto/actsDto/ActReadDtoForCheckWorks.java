package com.example.tradeintechniqueapp.dto.actsDto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class ActReadDtoForCheckWorks {
    Long id;
    LocalDate date;
    String number;
}
