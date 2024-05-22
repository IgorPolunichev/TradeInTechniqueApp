package com.example.tradeintechniqueapp.dto.actsDto;
import lombok.Value;
import java.time.LocalDate;

@Value
public class ActFrontPageDto {
    /*
    * А мы добавили вот тут
    * */

    Long id;
    LocalDate date;
    String number;
    String machineSerialNumber;
    String machineType;
    String actDescription;


}
