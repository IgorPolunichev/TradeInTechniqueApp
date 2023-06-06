package com.example.tradeintechniqueapp.dto.companiesDto;

import com.example.tradeintechniqueapp.database.entity.LocationCompany;
import lombok.Value;

@Value
public class CompanyReadDto {
    Long id;
    String nameCompany;
    String inn;
    String kpp;
    LocationCompany locationCompany;
}
