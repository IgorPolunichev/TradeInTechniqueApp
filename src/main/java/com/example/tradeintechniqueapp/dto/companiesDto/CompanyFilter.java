package com.example.tradeintechniqueapp.dto.companiesDto;

import com.example.tradeintechniqueapp.database.entity.User;
import lombok.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Value
public class CompanyFilter {
 String nameCompany;

}
