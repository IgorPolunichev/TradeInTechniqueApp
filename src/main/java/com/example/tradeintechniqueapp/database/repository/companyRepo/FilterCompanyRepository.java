package com.example.tradeintechniqueapp.database.repository.companyRepo;

import com.example.tradeintechniqueapp.database.entity.Company;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilterCompanyRepository {
    List<Company> findAllCompanyByFilter(CompanyFilter companyFilter, Pageable pageable);
}
