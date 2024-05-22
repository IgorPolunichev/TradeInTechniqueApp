package com.example.tradeintechniqueapp.mapper.actMappers;

import com.example.tradeintechniqueapp.database.entity.Act;
import com.example.tradeintechniqueapp.mapper.Mapper;

public class ActReadMapper implements Mapper<Act, ActReadMapper> {
    @Override
    public ActReadMapper map(Act object) {
        return null;
    }

    @Override
    public ActReadMapper map(Act fromObject, ActReadMapper toObject) {
        return Mapper.super.map(fromObject, toObject);
    }
}
