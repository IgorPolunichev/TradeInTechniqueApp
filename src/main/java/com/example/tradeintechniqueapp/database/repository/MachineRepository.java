package com.example.tradeintechniqueapp.database.repository;

import com.example.tradeintechniqueapp.dto.MachineDto2;
import com.example.tradeintechniqueapp.database.entity.Machine;
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

}
