package com.example.tradeintechniqueapp.http.restController;

import com.example.tradeintechniqueapp.dto.companiesDto.CompanyCreateEditDto;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyFilter;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyReadDto;
import com.example.tradeintechniqueapp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/companies")
public class CompanyRestController {

    private final CompanyService companyService;

    @GetMapping("/allCompaniesByFilter")
    public Page<CompanyReadDto> getCompaniesByFilter(CompanyFilter companyFilter, Pageable pageable) {
        return companyService.findAllCompanyByFilter(companyFilter, pageable);
    }
    @GetMapping("/{id}")
    public Optional<CompanyReadDto> getCompanyById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyReadDto createCompany(@RequestBody CompanyCreateEditDto company) {
        return companyService.create(company);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@PathVariable Long id) {
        return companyService.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping()
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    public Optional<CompanyReadDto> update(@RequestBody CompanyCreateEditDto company) {
        return companyService.update(company);
    }
}
