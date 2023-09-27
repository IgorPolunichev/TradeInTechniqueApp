package com.example.tradeintechniqueapp.dto.machinesDto;

import lombok.Value;

import java.time.LocalDate;
import java.time.Year;

@Value
public class MachineCreateEditDto {
    Long id;
    String type;
    String serialNumber;
    String subtype;
    int operatingTime;
    Year yearOfRelease;
    Long companyId;


}
