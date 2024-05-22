package com.example.tradeintechniqueapp.dto.actsDto;

import com.example.tradeintechniqueapp.database.entity.ActUser;
import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.database.entity.audit.ActPay;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineReadDto;
import com.example.tradeintechniqueapp.dto.usersDto.UserReadDto;
import com.example.tradeintechniqueapp.dto.workReadDto.WorkReadDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;

import java.time.LocalDate;
import java.util.List;

@Value
//@FieldNameConstants
public class ActReadDto {

    Long id;
    LocalDate date;

//    List<Work> works;
    List<WorkReadDto> works;
    String number;
    String numberApplication;
    MachineReadDto machine;
    String car;
    String licensePlate;
    String placeOfWork;
    String actDescription;
    ActPay actPay;
    String pathFiles;
    List<UserReadDto> users;

//    @Override
//    public String toString() {
//        return "ActReadDto{" +
//               "id=" + id +
//               ", date=" + date.toString() +
//               ", works=" + works +
//               ", number='" + number + '\'' +
//               ", numberApplication='" + numberApplication + '\'' +
//               ", machine=" + machine +
//               ", car='" + car + '\'' +
//               ", licensePlate='" + licensePlate + '\'' +
//               ", placeOfWork='" + placeOfWork + '\'' +
//               ", actDescription='" + actDescription + '\'' +
//               ", actPay=" + actPay +
//               ", pathFiles='" + pathFiles + '\'' +
//               '}';
//    }
}
