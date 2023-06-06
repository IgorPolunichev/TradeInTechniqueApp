package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.database.repository.partRepo.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;

}
