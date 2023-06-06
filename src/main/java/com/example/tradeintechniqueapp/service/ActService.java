package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.database.repository.actRepo.ActRepository;
import com.example.tradeintechniqueapp.database.repository.actRepo.ActUserRepository;
import com.example.tradeintechniqueapp.database.repository.userRepo.UserRepository;
import com.example.tradeintechniqueapp.dto.actsDto.ActCreateEditDto;
import com.example.tradeintechniqueapp.mapper.actMappers.ActCreateEditMapper;
import com.example.tradeintechniqueapp.mapper.userMappers.UserCreateEditMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
