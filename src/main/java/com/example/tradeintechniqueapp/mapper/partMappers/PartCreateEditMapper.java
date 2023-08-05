package com.example.tradeintechniqueapp.mapper.partMappers;

import com.example.tradeintechniqueapp.database.entity.Part;
import com.example.tradeintechniqueapp.dto.PartsDto.PartCreateEditDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PartCreateEditMapper implements Mapper<PartCreateEditDto, Part> {
    @Override
    public Part map(PartCreateEditDto object) {
        return Part.builder()
                .id(object.getId())
                .name(object.getName())
                .identNumber(object.getIdentNumber())
                .build();
    }

    @Override
    public Part map(PartCreateEditDto fromObject, Part toObject) {
        return Mapper.super.map(fromObject, toObject);
    }
}
