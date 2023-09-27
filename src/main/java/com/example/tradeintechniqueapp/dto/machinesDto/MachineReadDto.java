package com.example.tradeintechniqueapp.dto.machinesDto;

import lombok.Value;

import java.time.Year;

@Value
public class MachineReadDto {
    Long id;
    String type;
    String serialNumber;
    String subtype;
    int operatingTime;
    Year yearOfRelease;

    String companyName;
    Long companyId;
}
