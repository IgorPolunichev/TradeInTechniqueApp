package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.database.entity.Machine;
import com.example.tradeintechniqueapp.database.repository.machineRepo.MachineRepository;
import com.example.tradeintechniqueapp.database.repository.userRepo.UserRepository;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineCreateEditDto;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineDto;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineFilter;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineReadDto;
import com.example.tradeintechniqueapp.mapper.machineMappers.MachineCreateEditMapper;
import com.example.tradeintechniqueapp.mapper.machineMappers.MachineReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MachineService {
    private final MachineRepository machineRepository;
    private final MachineCreateEditMapper machineCreateEditMapper;
    private final MachineReadMapper machineReadMapper;
    private final UserRepository userRepository;

    public Optional<MachineDto> findBySerialNumber(String serialNumber) {
        Optional<Machine> bySerialNumber = machineRepository.findBySerialNumber(serialNumber);
        return bySerialNumber.isPresent() ? bySerialNumber.map(entity -> new MachineDto(entity.getId(),
                entity.getType(), entity.getSerialNumber())) : Optional.empty();
    }

    @Transactional
    public int updateCompany(Long id, String serialNumber) {
        int i = machineRepository.updateCompany(id, serialNumber);
        return i;
    }


    public MachineReadDto create(MachineCreateEditDto machine) {
        return Optional.of(machine)
                .map(machineCreateEditMapper::map)
                .map(machineRepository::save)
                .map(machineReadMapper::map)
                .orElseThrow();
    }

    public Page<MachineReadDto> findAll(MachineFilter machineFilter, Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matchingAny()
                .withMatcher("serialNumber", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Machine> machineExample = Example.of(Machine.builder()
                .serialNumber(machineFilter.serialNumber()).build(), exampleMatcher);
        Page<MachineReadDto> allWithCompany = machineRepository.findAll(machineExample, pageable).map(machineReadMapper::map);
        System.out.println();
        return allWithCompany;
    }

    public boolean delete(Long id) {
        return machineRepository.findById(id)
                .map(entity -> {
                    machineRepository.delete(entity);
                    return true;
                }).orElse(false);
    }
}
