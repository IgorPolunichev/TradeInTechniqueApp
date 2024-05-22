package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.database.entity.Act;
import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.database.repository.actRepo.ActCheckWorkRepository;
import com.example.tradeintechniqueapp.database.repository.actRepo.ActRepository;
import com.example.tradeintechniqueapp.database.repository.actRepo.ActUserRepository;
import com.example.tradeintechniqueapp.database.repository.userRepo.UserRepository;
import com.example.tradeintechniqueapp.dto.actsDto.ActCreateEditDto;
import com.example.tradeintechniqueapp.dto.actsDto.ActFrontPageDto;
import com.example.tradeintechniqueapp.dto.actsDto.ActReadDto;
import com.example.tradeintechniqueapp.dto.usersDto.CustomUserDetails;
import com.example.tradeintechniqueapp.dto.workReadDto.WorkCheckDto;
import com.example.tradeintechniqueapp.mapper.actMappers.ActCreateEditMapper;
import com.example.tradeintechniqueapp.mapper.actMappers.ActFrontPageMapper;
import com.example.tradeintechniqueapp.mapper.actMappers.ActReadMapper;
import com.example.tradeintechniqueapp.mapper.userMappers.UserReadMapper;
import com.example.tradeintechniqueapp.mapper.workMapper.WorkCheckMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActService {
    
    private final ActRepository actRepository;
    private final ActUserRepository actUserRepository;
    private final ActCheckWorkRepository actCheckWork;
    private final ActCreateEditMapper actCreateEditMapper;
    private final ActFrontPageMapper actFrontPageMapper;
    private final ActReadMapper actReadMapper;
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final WorkCheckMapper workCheckMapper;
    public final FileService fileService;




    @Transactional
    public ActReadDto create(ActCreateEditDto act) {
        CustomUserDetails u = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userRepository.findById(u.getId()).ifPresent(e -> {
            e.setCounterActs(e.getCounterActs() + 1);
            act.getUsers().add(userReadMapper.map(e));

        });
        return Optional.of(act)
                .map(actCreateEditMapper::map)
                .map(actRepository::save)
                .map(actReadMapper::map)
                .orElseThrow();
    }
@Lookup
    public Optional<List<ActFrontPageDto>> getActs() {
        Long authUser = getAuthUser().getId();
        Optional<List<ActFrontPageDto>> actFrontPageDtos = actUserRepository.findActUserByUser_Id(authUser)
                .map(
                        actUsers -> actUsers.stream()
                                .map(actFrontPageMapper::map)
                                .sorted(Comparator.comparing(ActFrontPageDto::getDate))
                                .toList()
                );
        return actFrontPageDtos;
    }

    public Optional<List<WorkCheckDto>> checkWork(Work work, Long id) {
        Optional<List<Work>> works = actCheckWork.checkWork(work, id);
        if (works.isEmpty()) {
            return Optional.empty();
        } else {
            return works.map(workCheckMapper::map);
        }
    }

    @Transactional
    public boolean deleteAct(Long id) {
        return actRepository.findById(id)
                .map(entity -> {
                    actRepository.delete(entity);
                    fileService.deleteDir(entity.getPathFiles());
                    return true;
                }).orElse(false);
    }


    private CustomUserDetails getAuthUser() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    public Optional<ActReadDto> getActById(Long id) {
        return actRepository.findById(id).map(actReadMapper::map);
    }
}
