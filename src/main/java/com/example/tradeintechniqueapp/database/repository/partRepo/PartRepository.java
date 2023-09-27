package com.example.tradeintechniqueapp.database.repository.partRepo;

import com.example.tradeintechniqueapp.database.entity.Part;
import org.apache.poi.sl.draw.geom.GuideIf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {

    Optional<Part> findByIdentNumber(String identNumber);
}
