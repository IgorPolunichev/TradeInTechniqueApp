package com.example.tradeintechniqueapp.http.restController;

import com.example.tradeintechniqueapp.database.entity.Position;
import com.example.tradeintechniqueapp.database.entity.Role;
import com.example.tradeintechniqueapp.dto.usersDto.UserCreateEditDto;
import com.example.tradeintechniqueapp.dto.usersDto.UserReadDto;
import com.example.tradeintechniqueapp.dto.usersDto.UserReadDtoForAdmin;
import com.example.tradeintechniqueapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/users")
    public Page<UserReadDtoForAdmin> getUsers(@PageableDefault(size = 20) Pageable pageable) {
        return userService.findAll(pageable);

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

    @PutMapping("/{id}")
    public Optional<UserReadDto>  update(@RequestBody UserCreateEditDto user , @PathVariable Long id) {
        return userService.update(id, user);

    }

    @DeleteMapping("/{id}")
    public boolean delete( @PathVariable Long id) {
        return userService.delete(id);

    }


}
