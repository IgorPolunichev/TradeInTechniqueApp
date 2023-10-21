package com.example.tradeintechniqueapp.database.repository.actRepo;

import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.dto.workReadDto.WorkCheckDto;

import java.util.List;
import java.util.Optional;

public interface ActCheckWorkRepository {
    Optional<List<Work>> checkWork(Work work, Long userId);

}
