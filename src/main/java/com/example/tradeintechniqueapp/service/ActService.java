package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.database.entity.Act;
import com.example.tradeintechniqueapp.database.entity.ActUser;
import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.database.repository.ActRepository;
import com.example.tradeintechniqueapp.database.repository.ActUserRepository;
import com.example.tradeintechniqueapp.database.repository.UserRepository;
import com.example.tradeintechniqueapp.dto.ActCreateEditDto;
import com.example.tradeintechniqueapp.dto.ActReadDto;
import com.example.tradeintechniqueapp.dto.CustomUserDetails;
import com.example.tradeintechniqueapp.mapper.ActCreateEditMapper;
import com.example.tradeintechniqueapp.mapper.UserCreateEditMapper;
import com.example.tradeintechniqueapp.mapper.UserReadMapper;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActService {

    private final ActRepository actRepository;
    private final ActUserRepository actUserRepository;
    private final UserRepository userRepository;
    private final ActCreateEditMapper actCreateEditMapper;
    private final UserCreateEditMapper userCreateEditMapper;

public boolean create(ActCreateEditDto act, Long authUserId){
//    ActUser actUser = new ActUser();
//    actUser.setUser(new User(authUserId));
//    Act act1 = actCreateEditMapper.map(act);
//    actUser.setAct(act1);
//    actUserRepository.save(actUser);
//    actRepository.save(act1);
//    userRepository.updateActCounterByUserId(authUserId);
    return true;

}

private String getNumberAct(Long authUser, Long lastNumberAct){
//    String lastNumberActByUser = actRepository.findLastAcyByUserId(authUser);
    return null;

}


}
