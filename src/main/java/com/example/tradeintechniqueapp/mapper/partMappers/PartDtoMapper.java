package com.example.tradeintechniqueapp.mapper.partMappers;

import com.example.tradeintechniqueapp.database.entity.Part;
import com.example.tradeintechniqueapp.dto.PartsDto.PartDto;
import com.example.tradeintechniqueapp.mapper.Mapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
public class PartDtoMapper implements Mapper<Part, PartDto> {
    @Override
    public PartDto map(Part object) {
        return new PartDto(object.getId(), object.getIdentNumber());
    }

    @Override
    public PartDto map(Part fromObject, PartDto toObject) {
        return Mapper.super.map(fromObject, toObject);
    }
}
