package com.example.tradeintechniqueapp.http.restController;

import com.example.tradeintechniqueapp.dto.PartsDto.PartCreateEditDto;
import com.example.tradeintechniqueapp.dto.PartsDto.PartDto;
import com.example.tradeintechniqueapp.dto.PartsDto.PartFilter;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyCreateEditDto;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyFilter;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyReadDto;
import com.example.tradeintechniqueapp.service.CompanyService;
import com.example.tradeintechniqueapp.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v4/parts")
public class PartRestController {

    private final PartService partService;

    @GetMapping("/allPartsByFilter")
    public Page<PartDto> getPartByFilter(PartFilter partFilter, Pageable pageable){
        return partService.findAll(partFilter, pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PartDto createParts(@RequestBody PartCreateEditDto part){
        return partService.create(part);
    }
    @PostMapping("/uploadPartsList")
    @ResponseStatus(HttpStatus.OK)
    public boolean uploadPartsList(@RequestBody MultipartFile file){
        return partService.uploadPartsList(file);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@PathVariable Long id){
        return partService.delete(id);

    }


}
