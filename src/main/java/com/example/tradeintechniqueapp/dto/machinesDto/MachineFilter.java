package com.example.tradeintechniqueapp.dto.machinesDto;

import lombok.Value;

//@Value
//public class MachineFilter {
//    String serialNumber;
//}

public record MachineFilter( String type, String serialNumber, Integer operatingTime) {
}
