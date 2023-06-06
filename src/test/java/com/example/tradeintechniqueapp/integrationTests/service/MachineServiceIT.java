package com.example.tradeintechniqueapp.integrationTests.service;

import com.example.tradeintechniqueapp.integrationTests.annotation.IT;
import com.example.tradeintechniqueapp.database.repository.companyRepo.CompanyRepository;
import lombok.RequiredArgsConstructor;

@IT
@RequiredArgsConstructor
public class MachineServiceIT {

    private final CompanyRepository companyRepository;



}
