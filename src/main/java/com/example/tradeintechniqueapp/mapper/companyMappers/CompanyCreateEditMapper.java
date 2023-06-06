package com.example.tradeintechniqueapp.mapper.companyMappers;

import com.example.tradeintechniqueapp.database.entity.Company;
import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyCreateEditDto;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyDto;
import com.example.tradeintechniqueapp.dto.usersDto.UserCreateEditDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyCreateEditMapper implements Mapper<CompanyCreateEditDto, Company> {

    @Override
    public Company map(CompanyCreateEditDto object) {
        Company company = new Company();
        copy(object, company);
        return company;
    }

    @Override
    public Company map(CompanyCreateEditDto fromObject, Company toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(CompanyCreateEditDto from, Company to){
        to.setInn(from.getInn());
        to.setKpp(from.getKpp());
        to.setNameCompany(from.getNameCompany());
        to.setLocationCompany(from.getLocationCompany());
    }
}
