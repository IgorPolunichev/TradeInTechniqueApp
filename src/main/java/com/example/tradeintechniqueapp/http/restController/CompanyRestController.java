package com.example.tradeintechniqueapp.http.restController;

import com.example.tradeintechniqueapp.database.repository.companyRepo.CompanyRepository;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyCreateEditDto;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyFilter;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyReadDto;
import com.example.tradeintechniqueapp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/companies")
public class CompanyRestController {

    private final CompanyService companyService;

    @GetMapping("/allCompaniesByFilter")
    public Page<CompanyReadDto> getCompaniesByFilter(CompanyFilter companyFilter, Pageable pageable){
        return companyService.findAllCompanyByFilter(companyFilter, pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyReadDto createCompany(@RequestBody CompanyCreateEditDto company){
        return companyService.create(company);
    }
}
