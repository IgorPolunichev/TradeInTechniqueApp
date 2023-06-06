package com.example.tradeintechniqueapp.mapper.machineMappers;

import com.example.tradeintechniqueapp.database.entity.Machine;
import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineReadDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MachineReadMapper implements Mapper<Machine, MachineReadDto> {
    @Override
    public MachineReadDto map(Machine object) {
        return new MachineReadDto(object.getId()
                , object.getType()
                , object.getSerialNumber()
                , object.getOperatingTime()
                , object.getYearOfRelease()
        ,object.getCompany().getNameCompany());
    }

    @Override
    public MachineReadDto map(Machine fromObject, MachineReadDto toObject) {
        return Mapper.super.map(fromObject, toObject);
    }

}
