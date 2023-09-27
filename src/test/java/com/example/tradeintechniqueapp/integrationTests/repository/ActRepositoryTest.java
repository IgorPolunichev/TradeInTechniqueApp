package com.example.tradeintechniqueapp.integrationTests.repository;

import com.example.tradeintechniqueapp.database.entity.*;
import com.example.tradeintechniqueapp.database.repository.actRepo.ActRepository;
import com.example.tradeintechniqueapp.database.repository.actRepo.ActUserRepository;
import com.example.tradeintechniqueapp.database.repository.companyRepo.CompanyRepository;
import com.example.tradeintechniqueapp.database.repository.userRepo.UserRepository;
import com.example.tradeintechniqueapp.integrationTests.service.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.TransactionManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ActRepositoryTest extends IntegrationTestBase {

    private final TransactionManager transactionManager;

    private final ActRepository actRepository;
    private final UserRepository userRepository;
    private final ActUserRepository actUserRepository;
    private final CompanyRepository companyRepository;

    @Test
    void checkActCounter() {

        User user = userRepository.findById(1L).get();


        Company company = companyRepository.findByNameCompany("SEVER_STAL").get();

        List<Work> works = new ArrayList<>();
        works.add(Work.builder().workDate(LocalDate.now())
                .startWork(LocalTime.of(12, 00))
                .endWork(LocalTime.of(14, 00))
                .workDescription("Выполнна работа по ТО 2000")
//                .workDescription("SET")
                .build());
        works.add(Work.builder().workDate(LocalDate.now())
                .startWork(LocalTime.of(14, 00))
                .endWork(LocalTime.of(15, 00))
                .workDescription("Выполнна диагностика системы кондиционирования")
//                .workDescription("SET_2")
                .build());

        Act testAct = Act.builder()
                .date(LocalDate.now())
                .works(works)
                .build();

        ActUser actUser = new ActUser();
        actUser.setAct(testAct);
        actUser.setUser(user);
        actUserRepository.save(actUser);
        User polusha = getUser("polusha");

        System.out.println(polusha);
    }

    
    public User getUser(String s) {
        User user = userRepository.findByUsername("polusha").get();
        user.getActUserList();
        return user;

    }

    @Test
    void checkUserCount()
    {
        System.out.println(userRepository.updateActCounterByUserId(1L));
    }

}
