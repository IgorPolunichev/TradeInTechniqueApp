package com.example.tradeintechniqueapp.dto.companiesDto;

import com.example.tradeintechniqueapp.database.entity.LocationCompany;
import lombok.Value;

/*
* 1
* */

@Value
public class CompanyCreateEditDto {
    /*
    * 1
    * */
    Long id;

    String nameCompany;

    String inn;
    String kpp;
    LocationCompany locationCompany;
}
