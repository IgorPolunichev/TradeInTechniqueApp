package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.database.entity.Part;
import com.example.tradeintechniqueapp.database.repository.partRepo.PartRepository;
import com.example.tradeintechniqueapp.dto.PartsDto.PartCreateEditDto;
import com.example.tradeintechniqueapp.dto.PartsDto.PartDto;
import com.example.tradeintechniqueapp.dto.PartsDto.PartFilter;
import com.example.tradeintechniqueapp.mapper.partMappers.PartCreateEditMapper;
import com.example.tradeintechniqueapp.mapper.partMappers.PartDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;
    private final PartCreateEditMapper partCreateEditMapper;
    private final PartDtoMapper partDtoMapper;
    private final PartsListService partsListService;

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

    @SneakyThrows
    public boolean uploadPartsList(MultipartFile file) {
        Path path = partsListService.upload(file.getOriginalFilename(), file.getInputStream());
        FileInputStream inputStream = new FileInputStream(path.toString());
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheetAt = workbook.getSheetAt(0);
        int lastRowNum = sheetAt.getLastRowNum();
        short lastCellNum = sheetAt.getRow(1).getLastCellNum();
        Set<Part> parts = new HashSet<>();
        for (int i = 0; i <= lastRowNum; i++) {
            Part part = new Part();
            for (int y = 0; y < lastCellNum; y++) {
                CellType cellType = sheetAt.getRow(i).getCell(y).getCellType();
                if (y == 0) {
                    switch (cellType) {
                        case STRING -> part.setIdentNumber(sheetAt.getRow(i).getCell(y).getStringCellValue());
                        case NUMERIC ->
                                part.setIdentNumber(String.valueOf((long) sheetAt.getRow(i).getCell(y).getNumericCellValue()));
                    }
                } else if (y == 1) {
                    part.setName(sheetAt.getRow(i).getCell(y).getStringCellValue());
                }
            }
            parts.add(part);
            if (parts.size() > 80) {
                Set<Part> collect = parts.stream().filter(entity -> partRepository
                                .findByIdentNumber(entity.getIdentNumber()).isEmpty())
                        .collect(Collectors.toSet());
                partRepository.saveAll(collect);
                parts.clear();
            }
        }
        return true;
    }


    public Page<PartDto> findAll(PartFilter partFilter, Pageable pageable) {
        String searchBy = "";
        if (partFilter.name() != null) {
            searchBy = "name";
        } else if (partFilter.partNumber() != null) {
            searchBy = "identNumber";
        }

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matchingAny()
                .withMatcher(searchBy, ExampleMatcher.GenericPropertyMatchers.startsWith());
        Example<Part> example = Example.of(Part.builder()
                .identNumber(partFilter.partNumber())
                .name(partFilter.name()).build(), exampleMatcher);
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
