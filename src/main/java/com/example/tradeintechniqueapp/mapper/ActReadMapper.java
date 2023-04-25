package com.example.tradeintechniqueapp.mapper;

import com.example.tradeintechniqueapp.database.entity.Act;

public class ActReadMapper implements Mapper<Act, ActReadMapper>{
    @Override
    public ActReadMapper map(Act object) {
        return null;
    }

    @Override
    public ActReadMapper map(Act fromObject, ActReadMapper toObject) {
        return Mapper.super.map(fromObject, toObject);
    }
}
