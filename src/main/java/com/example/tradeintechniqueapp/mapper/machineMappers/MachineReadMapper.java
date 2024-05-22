package com.example.tradeintechniqueapp.mapper.machineMappers;

import com.example.tradeintechniqueapp.database.entity.Machine;
import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineReadDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import com.example.tradeintechniqueapp.mapper.companyMappers.CompanyReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MachineReadMapper implements Mapper<Machine, MachineReadDto> {
    private final CompanyReadMapper companyReadMapper;
    @Override
    public MachineReadDto map(Machine object) {
        return new MachineReadDto(object.getId()
                , object.getType()
                , object.getSerialNumber()
                ,object.getSubtype()
                , object.getOperatingTime()
                , object.getYearOfRelease()
//        ,object.getCompany().getNameCompany()
        ,companyReadMapper.map(object.getCompany()));
    }

    @Override
    public MachineReadDto map(Machine fromObject, MachineReadDto toObject) {
        return Mapper.super.map(fromObject, toObject);
    }

}
