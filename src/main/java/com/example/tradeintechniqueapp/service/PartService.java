package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.database.entity.Part;
import com.example.tradeintechniqueapp.database.repository.partRepo.PartRepository;
import com.example.tradeintechniqueapp.dto.PartsDto.PartCreateEditDto;
import com.example.tradeintechniqueapp.dto.PartsDto.PartDto;
import com.example.tradeintechniqueapp.dto.PartsDto.PartFilter;
import com.example.tradeintechniqueapp.mapper.partMappers.PartCreateEditMapper;
import com.example.tradeintechniqueapp.mapper.partMappers.PartDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;
    private final PartCreateEditMapper partCreateEditMapper;
    private final PartDtoMapper partDtoMapper;

    public PartDto create(PartCreateEditDto part) {

        return Optional.of(part).
                map(partCreateEditMapper::map)
                .map(partRepository::save)
                .map(partDtoMapper::map).orElseThrow();
    }

    public Optional<PartDto> update(Long id, PartCreateEditDto part) {
        return partRepository.findById(id)
                .map(entity -> partCreateEditMapper.map(part))
                .map(partRepository::saveAndFlush)
                .map(partDtoMapper::map);
    }

    public Page<PartDto> findAll(PartFilter partFilter, Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("partNumber"
                , ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Part> example = Example.of(Part.builder().identNumber(partFilter.partNumber()).build(), exampleMatcher);
        return partRepository.findAll(example, pageable).map(partDtoMapper::map);
    }

    public boolean delete(Long id) {
        return partRepository.findById(id)
                .map(entity -> {
                    partRepository.delete(entity);
                    return true;
                }).orElse(false);
    }

}
