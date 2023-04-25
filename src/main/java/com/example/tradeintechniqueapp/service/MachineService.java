package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.dto.MachineDto;
import com.example.tradeintechniqueapp.database.entity.Machine;
import com.example.tradeintechniqueapp.database.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MachineService {
    private final MachineRepository machineRepository;

    public Optional<MachineDto> findBySerialNumber(String serialNumber){
        Optional<Machine> bySerialNumber = machineRepository.findBySerialNumber(serialNumber);

        return bySerialNumber.isPresent() ? bySerialNumber.map(entity -> new MachineDto(entity.getId() ,
                entity.getType(), entity.getSerialNumber() )) : Optional.empty();
    }
@Transactional
    public int updateCompany(Long id, String serialNumber){

        int i = machineRepository.updateCompany(id, serialNumber);
        return i;
    }


//    public Machine save(Machine machine){
//        return machineRepository.save(machine);
//    }
//
//    }
}
