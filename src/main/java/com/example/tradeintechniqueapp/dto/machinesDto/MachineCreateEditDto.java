package com.example.tradeintechniqueapp.dto.machinesDto;

import lombok.Value;

import java.time.LocalDate;
import java.time.Year;

@Value
public class MachineCreateEditDto {
    String type;
    String serialNumber;
    int operatingTime;
    Year yearOfRelease;
    Long companyId;


}
