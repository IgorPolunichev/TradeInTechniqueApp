package com.example.tradeintechniqueapp.dto.workReadDto;

import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
public class WorkReadDto {
    Long id;
    LocalDate workDate;
    LocalTime startWork;
    LocalTime endWork;
    LocalTime startLunch;
    LocalTime endLunch;
    String placeOfDeparture;
    String placeOfArrival;
    Integer mileage;
    String workDescription;
}
