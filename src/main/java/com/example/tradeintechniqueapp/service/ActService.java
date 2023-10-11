package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.database.entity.Act;
import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.database.repository.actRepo.ActCheckWorkRepository;
import com.example.tradeintechniqueapp.database.repository.actRepo.ActRepository;
import com.example.tradeintechniqueapp.database.repository.userRepo.UserRepository;
import com.example.tradeintechniqueapp.dto.actsDto.ActCreateEditDto;
import com.example.tradeintechniqueapp.dto.usersDto.CustomUserDetails;
import com.example.tradeintechniqueapp.dto.workReadDto.WorkCheckDto;
import com.example.tradeintechniqueapp.mapper.actMappers.ActCreateEditMapper;
import com.example.tradeintechniqueapp.mapper.userMappers.UserReadMapper;
import com.example.tradeintechniqueapp.mapper.workMapper.WorkCheckMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActService {

    private final ActRepository actRepository;
    private final UserRepository userRepository;
    private final ActCreateEditMapper actCreateEditMapper;
    private final UserReadMapper userReadMapper;
    private final WorkCheckMapper workCheckMapper;
    private final ActCheckWorkRepository actCheckWork;

    @Transactional
    public Act create(ActCreateEditDto act) {
        CustomUserDetails u = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userRepository.findById(u.getId()).ifPresent(e -> {
            e.setCounterActs(e.getCounterActs() + 1);
            act.getUsers().add(userReadMapper.map(e));

        });
        return actRepository.save(actCreateEditMapper.map(act));
    }


    private String getNumberAct(Long authUser, Long lastNumberAct) {
        return null;

    }

    public Optional<List<WorkCheckDto>> checkWork(Work work, Long id) {
        Optional<List<Work>> works = actCheckWork.checkWork(work, id);
        if (works.isEmpty()) {
            return Optional.empty();
        } else {
            return works.map(workCheckMapper::map);
        }
    }
}
