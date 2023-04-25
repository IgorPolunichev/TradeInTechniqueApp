package com.example.tradeintechniqueapp.mapper;

public interface Mapper <E, O>{

    O map(E object);

    default O map (E fromObject , O toObject){
        return toObject;
    }
}
