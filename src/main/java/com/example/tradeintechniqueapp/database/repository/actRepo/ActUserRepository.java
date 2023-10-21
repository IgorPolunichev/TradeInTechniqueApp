package com.example.tradeintechniqueapp.database.repository.actRepo;

import com.example.tradeintechniqueapp.database.entity.ActUser;
import com.example.tradeintechniqueapp.dto.actsDto.ActFrontPageDto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActUserRepository extends JpaRepository<ActUser, Long> {
    @EntityGraph(attributePaths = {"act"})
    Optional<List<ActUser>> findActUserByUser_Id(Long id);
}
