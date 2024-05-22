package com.example.tradeintechniqueapp.mapper.actMappers;

import com.example.tradeintechniqueapp.database.entity.Act;
import com.example.tradeintechniqueapp.database.entity.ActUser;
import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.dto.actsDto.ActReadDto;
import com.example.tradeintechniqueapp.dto.usersDto.CustomUserDetails;
import com.example.tradeintechniqueapp.dto.usersDto.UserReadDto;
import com.example.tradeintechniqueapp.dto.workReadDto.WorkReadDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import com.example.tradeintechniqueapp.mapper.machineMappers.MachineReadMapper;
import com.example.tradeintechniqueapp.mapper.userMappers.UserReadMapper;
import com.example.tradeintechniqueapp.mapper.workMapper.WorkReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ActReadMapper implements Mapper<Act, ActReadDto> {

    private final MachineReadMapper machineReadMapper;
    private final WorkReadMapper workReadMapper;
    private final UserReadMapper userReadMapper;

    @Override
    public ActReadDto map(Act object) {
        List<WorkReadDto> list = new ArrayList<>();
        for (Work w : object.getWorks()) {
            list.add(workReadMapper.map(w));
        }
        List<UserReadDto> users = new ArrayList<>();
        CustomUserDetails u = (CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        for (ActUser actUser : object.getActUserList()) {
            if(!Objects.equals(u.getId(), actUser.getUser().getId()))
            {
                users.add(userReadMapper.map(actUser.getUser()));
            }
        }


        return new ActReadDto(object.getId()
                , object.getDate()
                , list
                , object.getNumber()
                , object.getNumberApplication()
                , machineReadMapper.map(object.getMachine())
                , object.getCar()
                , object.getLicensePlate()
                , object.getPlaceOfWork()
                , object.getActDescription()
                , object.getActPay()
                , object.getPathFiles()
                , users);
    }

    @Override
    public ActReadDto map(Act fromObject, ActReadDto toObject) {
        return Mapper.super.map(fromObject, toObject);
    }
}
