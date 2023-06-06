package com.example.tradeintechniqueapp.database.repository.companyRepo;

import com.example.tradeintechniqueapp.database.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>,FilterCompanyRepository {

    Optional<Company> findByNameCompany(String nameCompany);



    @EntityGraph(attributePaths = {"machines"})
    @Query(value = "select c from Company c where c.nameCompany = :nameCompany")
    Optional<Company> findByNameCompanyWithMachines(String nameCompany);





}
