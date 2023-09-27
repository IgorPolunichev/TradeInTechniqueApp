package com.example.tradeintechniqueapp.dto.localCompaniDto;

import jakarta.persistence.Column;
import lombok.Value;

@Value
public class LocalCompanyReadDto {
     String city;
     String street;

     String house;

     String zipCode;
}
