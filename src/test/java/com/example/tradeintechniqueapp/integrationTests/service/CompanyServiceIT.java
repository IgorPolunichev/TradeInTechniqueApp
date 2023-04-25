package com.example.tradeintechniqueapp.integrationTests.service;

import com.example.tradeintechniqueapp.integrationTests.annotation.IT;
import com.example.tradeintechniqueapp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.predicate.BooleanExpressionPredicate;
import org.junit.jupiter.api.Test;

@IT
@RequiredArgsConstructor
public class CompanyServiceIT {

    private final CompanyService companyService;

    @Test
    void saveCompany(){
        String s = "test";


    }


}
