package com.example.tradeintechniqueapp.mapper.localCompanyMapper;

import com.example.tradeintechniqueapp.database.entity.LocationCompany;
import com.example.tradeintechniqueapp.dto.localCompaniDto.LocalCompanyReadDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class LocalCompanyMapper implements Mapper<LocationCompany, LocalCompanyReadDto> {
    @Override
    public LocalCompanyReadDto map(LocationCompany object) {
        return new LocalCompanyReadDto(object.getCity(), object.getStreet(), object.getHouse(), object.getZipCode());
    }

    @Override
    public LocalCompanyReadDto map(LocationCompany fromObject, LocalCompanyReadDto toObject) {
        return Mapper.super.map(fromObject, toObject);
    }
}
