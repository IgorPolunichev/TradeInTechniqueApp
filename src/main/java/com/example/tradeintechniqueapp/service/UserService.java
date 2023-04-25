package com.example.tradeintechniqueapp.service;

import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.database.repository.UserRepository;
import com.example.tradeintechniqueapp.dto.CustomUserDetails;
import com.example.tradeintechniqueapp.dto.UserCreateEditDto;
import com.example.tradeintechniqueapp.dto.UserDto;
import com.example.tradeintechniqueapp.dto.UserReadDto;
import com.example.tradeintechniqueapp.mapper.UserCreateEditMapper;
import com.example.tradeintechniqueapp.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return userRepository.findAll().stream().map(userReadMapper::map).collect(Collectors.toList());
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
                        new CustomUserDetails(user.getId(),user.getCounterActs(), user.getUsername(),
                user.getPassword(),
                Collections.singleton(user.getRole()))).
                orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user:" + username));
    }
}

