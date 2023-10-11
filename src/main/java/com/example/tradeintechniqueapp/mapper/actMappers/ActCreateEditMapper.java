package com.example.tradeintechniqueapp.mapper.actMappers;

import com.example.tradeintechniqueapp.database.entity.Act;
import com.example.tradeintechniqueapp.database.entity.ActParts;
import com.example.tradeintechniqueapp.database.entity.ActUser;
import com.example.tradeintechniqueapp.database.entity.Machine;
import com.example.tradeintechniqueapp.database.repository.machineRepo.MachineRepository;
import com.example.tradeintechniqueapp.database.repository.partRepo.PartRepository;
import com.example.tradeintechniqueapp.database.repository.userRepo.UserRepository;
import com.example.tradeintechniqueapp.dto.PartsDto.PartReadDto;
import com.example.tradeintechniqueapp.dto.actsDto.ActCreateEditDto;
import com.example.tradeintechniqueapp.dto.usersDto.UserReadDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import com.example.tradeintechniqueapp.mapper.machineMappers.MachineReadMapper;
import com.example.tradeintechniqueapp.mapper.userMappers.UserCreateEditMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActCreateEditMapper implements Mapper<ActCreateEditDto, Act> {

    private final MachineRepository machineRepository;
    private final UserRepository userRepository;
    private final PartRepository partRepository;

    @Override
    public Act map(ActCreateEditDto object) {
        Act act = new Act();
        copy(object, act);
        return act;
    }

    @Override
    public Act map(ActCreateEditDto fromObject, Act toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ActCreateEditDto fromObject, Act toObject){
        toObject.setDate(fromObject.getDate());
        toObject.setWorks(fromObject.getWorks());
        toObject.setNumber(fromObject.getNumber());
        toObject.setNumberApplication(fromObject.getNumberApplication());
        toObject.setCar(fromObject.getCar());
        toObject.setLicensePlate(fromObject.getLicensePlate());
        toObject.setPlaceOfWork(fromObject.getPlaceOfWork());
        toObject.setActPay(fromObject.getActPay());
        toObject.setActDescription(fromObject.getActDescription());

        machineRepository.findBySerialNumber(fromObject.getMachine().getSerialNumber())
                .ifPresent(machine-> {
                    machine.setOperatingTime(fromObject.getMachine().getOperatingTime());
                    toObject.setMachine(machine);
                });

        for(UserReadDto user : fromObject.getUsers()){
            ActUser actUser = new ActUser();
            userRepository.findByUsername(user.getUserName())
                    .ifPresent(actUser::setUser);
            actUser.setAct(toObject);
        }

        for (PartReadDto part : fromObject.getParts()){
            ActParts actParts = new ActParts();
            actParts.setPart(partRepository.findById(part.getId()).get());
            actParts.setAct(toObject);
            actParts.setCount(part.getCount());
            actParts.setOwner(part.getOwner());
        }
    }


}
