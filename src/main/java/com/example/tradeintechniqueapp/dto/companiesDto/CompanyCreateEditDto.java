package com.example.tradeintechniqueapp.dto.companiesDto;

import com.example.tradeintechniqueapp.database.entity.LocationCompany;
import lombok.Value;

/*
* 1
* 2
* 3
* 4
* */

@Value
public class CompanyCreateEditDto {
    Long id;

    String nameCompany;

    String inn;
    String kpp;
    LocationCompany locationCompany;
}
