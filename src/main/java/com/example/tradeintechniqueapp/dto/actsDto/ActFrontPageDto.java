package com.example.tradeintechniqueapp.dto.actsDto;
import lombok.Value;
import java.time.LocalDate;


/* Добавление многострочного комментария.
*
*
*
* */


/*Второй многострочный комментарий
*
*
*
*
* */
@Value
public class ActFrontPageDto {
/*
*HHHH
*
*
* */

    Long id;
    LocalDate date;
    String number;
    String machineSerialNumber;
    String machineType;
    String actDescription;


}
