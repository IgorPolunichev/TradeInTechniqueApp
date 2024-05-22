package com.example.tradeintechniqueapp.mapper.workMapper;

import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.dto.workReadDto.WorkReadDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class WorkReadMapper implements Mapper<Work, WorkReadDto> {
    @Override
    public WorkReadDto map(Work object) {
        return new WorkReadDto(object.getId()
                , object.getWorkDate()
                , object.getStartWork()
                , object.getEndWork()
                , object.getStartLunch()
                , object.getEndLunch()
                , object.getPlaceOfDeparture()
                , object.getPlaceOfArrival()
                , object.getMileage()
                , object.getWorkDescription());
    }

    @Override
    public WorkReadDto map(Work fromObject, WorkReadDto toObject) {
        return Mapper.super.map(fromObject, toObject);
    }

    public WorkReadDto copy(Work obj) {
        return null;


    }
}
