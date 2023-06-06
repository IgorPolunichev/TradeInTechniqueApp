package com.example.tradeintechniqueapp.dto.companiesDto;

import com.example.tradeintechniqueapp.database.entity.LocationCompany;
import lombok.Value;

@Value
public class CompanyCreateEditDto {
    Long id;

    String nameCompany;

    String inn;
    String kpp;
    LocationCompany locationCompany;
}
