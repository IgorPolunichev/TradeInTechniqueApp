package com.example.tradeintechniqueapp.mapper.companyMappers;

import com.example.tradeintechniqueapp.database.entity.Company;
import com.example.tradeintechniqueapp.database.entity.LocationCompany;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyReadDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {
    @Override
    public CompanyReadDto map(Company object) {

        return new CompanyReadDto(
                object.getId()
                , object.getNameCompany()
                , object.getInn()
                , object.getKpp()
                , new LocationCompany(object.getLocationCompany().getId()
                , object.getLocationCompany().getCity()
                ,object.getLocationCompany().getStreet()
        ,object.getLocationCompany().getHouse()
        ,object.getLocationCompany().getZipCode())
        );
    }

    @Override
    public CompanyReadDto map(Company fromObject, CompanyReadDto toObject) {
        return Mapper.super.map(fromObject, toObject);
    }

    private void copy(Company from, CompanyReadDto to) {

    }
}
