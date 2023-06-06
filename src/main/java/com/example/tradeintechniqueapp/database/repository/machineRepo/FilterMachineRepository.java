package com.example.tradeintechniqueapp.database.repository.machineRepo;

import com.example.tradeintechniqueapp.dto.machinesDto.MachineDto;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineFilter;
import com.example.tradeintechniqueapp.database.entity.Machine;

import java.util.List;

public interface FilterMachineRepository {
    List<Machine> findAllByFilter(MachineFilter machineFilter);

    List<MachineDto> findAllByCompanyIdAndType(Long companyIId, String type);

    void updateCompany(List<Machine> machines);

}
