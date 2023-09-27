package com.example.tradeintechniqueapp.database.repository.localCompanyRepo;

import com.example.tradeintechniqueapp.database.entity.LocationCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalCompanyRepo extends JpaRepository<LocationCompany, Long> {

    Optional<LocationCompany> findByCityAndStreetAndHouseAndZipCode(String city, String street, String house, String zipCode);
}
