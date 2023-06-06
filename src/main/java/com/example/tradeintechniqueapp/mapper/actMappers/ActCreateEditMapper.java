package com.example.tradeintechniqueapp.mapper.actMappers;

import com.example.tradeintechniqueapp.database.entity.Act;
import com.example.tradeintechniqueapp.dto.actsDto.ActCreateEditDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActCreateEditMapper implements Mapper<ActCreateEditDto, Act> {

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
        toObject.setActUserList(fromObject.getUsers());


    }


}
