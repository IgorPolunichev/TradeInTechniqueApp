package com.example.tradeintechniqueapp.database.repository.machineRepo;

import com.example.tradeintechniqueapp.database.entity.Machine;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineDto2;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long>,
        FilterMachineRepository,
        RevisionRepository<Machine, Long, Long> {

    Optional<Machine> findBySerialNumber(String serialNumber);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Machine m " +
           "set m.company.id = :id " +
           "where m.serialNumber = :serialNumber")
    int updateCompany(Long id, String serialNumber);

    @Query(value = "SELECT *" +
                   "  FROM machine " +
                   "WHERE company_id = :id",
            nativeQuery = true)
    List<MachineDto2> findAllByCompanyId(Long id);

    @EntityGraph(attributePaths = {"company"})
    Page<Machine> findAll(Example example, Pageable pageable);


}
