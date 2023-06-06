package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.database.entity.audit.AuditingEntity;
import com.example.tradeintechniqueapp.database.repository.actRepo.ActUserRepository;
import com.example.tradeintechniqueapp.database.repository.partRepo.PartRepository;
import com.example.tradeintechniqueapp.database.repository.userRepo.UserRepository;
import com.example.tradeintechniqueapp.dto.usersDto.*;
import com.example.tradeintechniqueapp.mapper.userMappers.UserCreateEditMapper;
import com.example.tradeintechniqueapp.mapper.userMappers.UserReadForAdminMapper;
import com.example.tradeintechniqueapp.mapper.userMappers.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserReadForAdminMapper userReadForAdminMapper;
    private final ActUserRepository actUserRepository;

    public Optional<UserDto> findByUserName(String username) {
        Optional<User> byUserName = userRepository.findByUsername(username);
        return Optional.of(new UserDto(byUserName.get().getId(),
                byUserName.get().getUsername()));
    }

    public UserDto saveAndFlush(User user) {
        User saveUser = userRepository.saveAndFlush(user);
        return new UserDto(saveUser.getId(), saveUser.getUsername());
    }

    public List<UserReadDto> findAll() {
        List<? extends AuditingEntity<Long>> list = userRepository.findAll();
        System.out.println(list.get(0).getCreatedAt());
        return userRepository.findAll()
                .stream()
                .map(userReadMapper::map)
                .collect(Collectors.toList());
    }

    public Page<UserReadDtoForAdmin> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userReadForAdminMapper::map);
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id).map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto user) {
        return Optional.of(user).
                map(userCreateEditMapper::map).
                map(userRepository::save).
                map(userReadMapper::map).
                orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id).
                map(entity -> userCreateEditMapper.map(userDto, entity)).
                map(userRepository::saveAndFlush).
                map(userReadMapper::map);

    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository
                .findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    return true;
                }).orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(user ->
                        new CustomUserDetails(user.getId(), user.getCounterActs(), user.getUsername(),
                                user.getPassword(),
                                Collections.singleton(user.getRole()))).
                orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user:" + username));
    }
}

