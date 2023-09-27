package com.example.tradeintechniqueapp.mapper.machineMappers;

import com.example.tradeintechniqueapp.database.entity.Company;
import com.example.tradeintechniqueapp.database.entity.Machine;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineCreateEditDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MachineCreateEditMapper implements Mapper<MachineCreateEditDto, Machine> {
    @Override
    public Machine map(MachineCreateEditDto object) {
        Machine machine = new Machine();
        copy(object, machine);
        return machine;
    }

    @Override
    public Machine map(MachineCreateEditDto fromObject, Machine toObject) {
        return Mapper.super.map(fromObject, toObject);
    }

    private void copy(MachineCreateEditDto from, Machine to) {
        to.setId(from.getId());
        to.setCompany(Company.builder()
                .id(from.getCompanyId())
                .build());
        to.setType(from.getType());
        to.setOperatingTime(from.getOperatingTime());
        to.setSerialNumber(from.getSerialNumber());
        to.setSubtype(from.getSubtype());
        to.setYearOfRelease(from.getYearOfRelease());

    }

}
