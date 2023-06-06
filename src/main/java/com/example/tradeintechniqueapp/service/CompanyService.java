package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.database.entity.Company;
import com.example.tradeintechniqueapp.database.repository.companyRepo.CompanyRepository;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyCreateEditDto;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyDto;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyFilter;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyReadDto;
import com.example.tradeintechniqueapp.mapper.companyMappers.CompanyCreateEditMapper;
import com.example.tradeintechniqueapp.mapper.companyMappers.CompanyReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyCreateEditMapper companyCreateEditMapper;
    private final CompanyReadMapper companyReadMapper;


    @Transactional
    public CompanyReadDto create(CompanyCreateEditDto company) {
        return Optional.of(company)
                .map(companyCreateEditMapper::map)
                .map(companyRepository::save)
                .map(companyReadMapper::map)
                .orElseThrow();
    }

    public CompanyDto saveAndFlush(Company company) {
        Company saveCompany = companyRepository.save(company);
        return new CompanyDto(saveCompany.getId(), saveCompany.getNameCompany());
    }

    public Optional<Company> findByNameCompany(String nameCompany) {
        return companyRepository.findByNameCompany(nameCompany);
    }

    public Optional<Company> findByNameCompanyFofUpdateMachines(String nameCompany) {
        return companyRepository.findByNameCompanyWithMachines(nameCompany);

    }

    public Page<CompanyReadDto> findAllCompanyByFilter(CompanyFilter companyFilter, Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("nameCompany", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Company> companyExample = Example.of(Company.builder()
                .nameCompany(companyFilter.getNameCompany())
                .build(), exampleMatcher);
        Page<CompanyReadDto> map = companyRepository.findAll(companyExample, pageable).map(companyReadMapper::map);
        return map;

    }


}
