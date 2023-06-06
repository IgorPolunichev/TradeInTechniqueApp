package com.example.tradeintechniqueapp.integrationTests.service;

import com.example.tradeintechniqueapp.database.entity.Position;
import com.example.tradeintechniqueapp.database.entity.Role;
import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.dto.usersDto.UserCreateEditDto;
import com.example.tradeintechniqueapp.dto.usersDto.UserDto;
import com.example.tradeintechniqueapp.dto.usersDto.UserReadDto;
import com.example.tradeintechniqueapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {
    private final Long USER_ID = 1L;
    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        Assertions.assertThat(result).hasSize(1);
    }

    @Test
    void findById() {
        Optional<UserReadDto> userById = userService.findById(USER_ID);
        assertTrue(userById.isPresent());
        userById.ifPresent(user -> assertEquals("polusha", user.getUserName()));
    }

    @Test
    void create() {
        UserCreateEditDto newUser = new UserCreateEditDto("petrovich"
                , "Vladlena"
                , "Polunicheva"
                , "Vladimirobna"
                , LocalDate.of(1989, 3, 15)
                , Role.USER
                , Position.MECHANIC
                , "123");
        UserReadDto actualResult = userService.create(newUser);
        assertEquals(newUser.getUsername(), actualResult.getUserName());
        assertEquals(newUser.getFirstname(), actualResult.getFirstname());
    }

    @Test
    void update() {
        UserCreateEditDto newUser = new UserCreateEditDto("petrovich"
                , "Vladlena"
                , "Polunicheva"
                , "Vladimirobna"
                , LocalDate.of(1989, 3, 15)
                , Role.USER
                , Position.MECHANIC
                , "1234");
        Optional<UserReadDto> actualResult = userService.update(USER_ID, newUser);
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> assertEquals("petrovich", user.getUserName()));

    }

    @Test
    void delete() {
        boolean delete = userService.delete(USER_ID);
        assertTrue(delete);
    }


    @Test
    void findByUserName() {
        Optional<UserDto> polusha = userService.findByUserName("polusha");
        System.out.println(polusha.isPresent() ? polusha.get().userName() : "Entity is not found");
    }

    @Test
    void saveUser() {
        UserDto userDto = userService.saveAndFlush(User.builder().
                username("polusha").
                position(Position.SERVICE_ENGINEER).
                role(Role.ADMIN).
                firstname("Igor").
                surname("Alexandrovich").
                lastname("Polunichev").
                birthDate(LocalDate.of(1986, 4, 9))
                .build());

        System.out.println(userDto.userName());

    }
}
