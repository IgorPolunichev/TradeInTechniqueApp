package com.example.tradeintechniqueapp.dto.companiesDto;

import com.example.tradeintechniqueapp.database.entity.LocationCompany;
import com.example.tradeintechniqueapp.dto.localCompaniDto.LocalCompanyReadDto;
import lombok.Value;

@Value
public class CompanyReadDto {
    Long id;
    String nameCompany;
    String inn;
    String kpp;
    LocalCompanyReadDto locationCompany;
}
