package com.example.tradeintechniqueapp.dto.PartsDto;

import com.example.tradeintechniqueapp.database.entity.Owner;
import lombok.Value;

@Value
public class PartReadDto {
    Long id;
    String identNumber;
    String name;
    Integer count;
    Owner owner;
}
