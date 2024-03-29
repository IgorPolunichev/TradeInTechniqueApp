package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.database.entity.Company;
import com.example.tradeintechniqueapp.database.entity.LocationCompany;
import com.example.tradeintechniqueapp.database.repository.companyRepo.CompanyRepository;
import com.example.tradeintechniqueapp.database.repository.localCompanyRepo.LocalCompanyRepo;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyCreateEditMapper companyCreateEditMapper;
    private final CompanyReadMapper companyReadMapper;
    private final LocalCompanyRepo localCompanyRepo;


    @Transactional
    public CompanyReadDto create(CompanyCreateEditDto company) {
        return Optional.of(company)
                .map(entity -> {
                    Optional<LocationCompany> byCityAndStreetAndHouseAndZipCode = localCompanyRepo
                            .findByCityAndStreetAndHouseAndZipCode(entity.getLocationCompany().getCity()
                                    ,entity.getLocationCompany().getStreet()
                            ,entity.getLocationCompany().getHouse()
                            ,entity.getLocationCompany().getZipCode());
                    if (byCityAndStreetAndHouseAndZipCode.isPresent()){
                        entity.getLocationCompany().setId(byCityAndStreetAndHouseAndZipCode.get().getId());
                    } else {
                        LocationCompany save = localCompanyRepo.save(company.getLocationCompany());
                        entity.getLocationCompany().setId(save.getId());
                    }
                    return entity;
                })
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
    public Optional<CompanyReadDto> findById(Long id){
        return companyRepository.findById(id)
                .map(companyReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        Optional<Company> byId = companyRepository.findByIdWithLocationCompany(id);
        companyRepository.delete(byId.get());
        return true;
//        return companyRepository.findById(id).map(entity -> {
//            companyRepository.delete(entity);
//            return true;
//        }).orElse(false);
    }

    @Transactional
    public Optional<CompanyReadDto> update(CompanyCreateEditDto company) {
        return companyRepository.findByIdWithLocationCompany(company.getId())
                .map(entity -> companyCreateEditMapper.map(company, entity))
                .map(companyRepository::saveAndFlush)
                .map(companyReadMapper::map);
    }
}
