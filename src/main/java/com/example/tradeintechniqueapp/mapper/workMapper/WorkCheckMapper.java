package com.example.tradeintechniqueapp.mapper.workMapper;

import com.example.tradeintechniqueapp.database.entity.Machine;
import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.dto.actsDto.ActReadDtoForCheckWorks;
import com.example.tradeintechniqueapp.dto.workReadDto.WorkCheckDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class WorkCheckMapper implements Mapper<List<Work>, List<WorkCheckDto>> {
    @Override
    public List<WorkCheckDto> map(List<Work> object) {
        List<WorkCheckDto> workCheckDtos = new ArrayList<>();

        for (Work w : object){
            workCheckDtos.add(new WorkCheckDto(w.getWorkDate()
            ,w.getStartWork()
            ,w.getEndWork()
            ,new ActReadDtoForCheckWorks(w.getAct().getId()
            ,w.getAct().getDate()
            ,w.getAct().getNumber())));
        }

        return workCheckDtos;
    }

}
