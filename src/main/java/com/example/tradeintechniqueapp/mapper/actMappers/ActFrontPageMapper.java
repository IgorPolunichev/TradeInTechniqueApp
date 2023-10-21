package com.example.tradeintechniqueapp.mapper.actMappers;

import com.example.tradeintechniqueapp.database.entity.Act;
import com.example.tradeintechniqueapp.database.entity.ActUser;
import com.example.tradeintechniqueapp.dto.actsDto.ActFrontPageDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActFrontPageMapper implements Mapper<ActUser, ActFrontPageDto> {
    @Override
    public ActFrontPageDto map(ActUser object) {
        return new ActFrontPageDto(object.getAct().getId()
                , object.getAct().getDate()
                , object.getAct().getNumber()
                , object.getAct().getMachine().getSerialNumber()
                ,object.getAct().getMachine().getType()
                , object.getAct().getActDescription());
    }

    @Override
    public ActFrontPageDto map(ActUser fromObject, ActFrontPageDto toObject) {
        return Mapper.super.map(fromObject, toObject);
    }


}
