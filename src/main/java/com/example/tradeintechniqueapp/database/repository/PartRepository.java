package com.example.tradeintechniqueapp.database.repository;

import com.example.tradeintechniqueapp.database.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
