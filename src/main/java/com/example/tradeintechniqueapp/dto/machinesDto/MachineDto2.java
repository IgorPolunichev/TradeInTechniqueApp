package com.example.tradeintechniqueapp.dto.machinesDto;

import org.springframework.beans.factory.annotation.Value;

public interface MachineDto2 {

    String getType();

    String getSerial_number();

    Integer getOperating_time();
@Value("#{target.type + ' ' + target.serial_number}")
    String getAllData();
}
