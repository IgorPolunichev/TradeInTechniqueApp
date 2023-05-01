package com.example.tradeintechniqueapp.http.restController;

import com.example.tradeintechniqueapp.database.entity.Position;
import com.example.tradeintechniqueapp.database.entity.Role;
import com.example.tradeintechniqueapp.database.repository.UserRepository;
import com.example.tradeintechniqueapp.dto.UserCreateEditDto;
import com.example.tradeintechniqueapp.dto.UserReadDto;
import com.example.tradeintechniqueapp.mapper.UserCreateEditMapper;
import com.example.tradeintechniqueapp.mapper.UserReadMapper;
import com.example.tradeintechniqueapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
//@RequestMapping(value = "foos", produces = MediaType.APPLICATION_JSON_VALUE)
//@OpenAPIDefinition(info = @Info(title = "Foos API", version = "v1"))
public class UserRestController {

    private final UserService userService;


    @GetMapping(value = "/roles")
    public List<Role> getRole() {
        return List.of(Role.values());

    }

    @GetMapping(value = "/positions")
    public List<Position> getPosition() {
        return List.of(Position.values());
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserReadDto createUser(@RequestBody UserCreateEditDto user) {
        return userService.create(user);

    }
}
