package com.example.tradeintechniqueapp.integrationTests.repository;

import com.example.tradeintechniqueapp.dto.machinesDto.MachineDto;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineDto2;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineFilter;
import com.example.tradeintechniqueapp.database.entity.Company;
import com.example.tradeintechniqueapp.database.entity.Machine;
import com.example.tradeintechniqueapp.database.repository.machineRepo.MachineRepository;
import com.example.tradeintechniqueapp.integrationTests.service.IntegrationTestBase;
import com.example.tradeintechniqueapp.service.CompanyService;
import com.example.tradeintechniqueapp.service.MachineService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RequiredArgsConstructor
public class MachineRepositoryIT extends IntegrationTestBase {

    private final MachineRepository machineRepository;
    private final CompanyService companyService;
    private final MachineService machineService;
    private  final EntityManager entityManager;

    @Test
    void checkBatch(){
        List<Machine> machines = machineRepository.findAll();
        machineRepository.updateCompany(machines);
        System.out.println();
    }

    @Test
    void checkJdbcTemplate(){
        List<MachineDto> pr776 = machineRepository.findAllByCompanyIdAndType(1L, "PR776");

        System.out.println();
    }

    @Test
    void projectionTest(){

        List<MachineDto2> findAllByCompanyId = machineRepository.findAllByCompanyId(1L);
        assertThat(findAllByCompanyId).hasSize(1);
        System.out.println();

    }

//    @Test
//    void saveMachineInCompany() {
//        Optional<Machine> bySerialNumber = machineRepository.findBySerialNumber("20128");
//        Optional<Company> company = companyService.findByNameCompanyFofUpdateMachines("SEVER_STAL");
//        if (company.isPresent() && (bySerialNumber.isPresent() && (bySerialNumber.get().getCompany() == null))) {
//            Long l = company.get().getId();
//            String s = bySerialNumber.get().getSerialNumber();
//            machineService.updateCompany(l, s);
////             }
//        }
//
//        Optional<Company> company2 = companyService.findByNameCompanyFofUpdateMachines("SEVER_STAL");
//        List<Machine> m = company2.get().getMachines();
//        System.out.println();
//    }

    @Test
    void checkCustomImpl(){
        MachineFilter machineFilter = new MachineFilter(null, "%20%", null );
        List<Machine> allByFilter = machineRepository.findAllByFilter(machineFilter);
        System.out.println();
    }

    @Test
    @Commit
    void checkAuditing(){
        Machine machine = machineRepository.findById(1L).get();
        Machine pr776 = Machine.builder().yearOfRelease(Year.of(2020)).serialNumber("19118").type("PR776").build();
        machineRepository.save(pr776);
        machine.setYearOfRelease(machine.getYearOfRelease().plusYears(1));
        machineRepository.flush();
        System.out.println();
    }
}
