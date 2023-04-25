package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.dto.CompanyDto;
import com.example.tradeintechniqueapp.database.entity.Company;
import com.example.tradeintechniqueapp.database.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyDto saveAndFlush(Company company) {
        Company saveCompany = companyRepository.save(company);
        return new CompanyDto(saveCompany.getId(), saveCompany.getNameCompany());
    }

    public Optional<Company> findByNameCompany(String nameCompany) {
        return companyRepository.findByNameCompany(nameCompany);
    }

    public Optional<Company> findByNameCompanyFofUpdateMachines(String nameCompany){
        return companyRepository.findByNameCompanyWithMachines(nameCompany);

    }



}
