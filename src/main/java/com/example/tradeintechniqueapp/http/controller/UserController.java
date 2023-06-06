package com.example.tradeintechniqueapp.http.controller;

import com.example.tradeintechniqueapp.database.entity.Position;
import com.example.tradeintechniqueapp.database.entity.Role;
import com.example.tradeintechniqueapp.dto.usersDto.UserCreateEditDto;
import com.example.tradeintechniqueapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public String findAllUsers(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }
    @GetMapping("/listMachines")
    public String findAllMachines() {
        return "user/machines";
    }
    @GetMapping("/listCompanies")
    public String findAllCompanies() {
        return "user/companies";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id
            , Model model
            , @CurrentSecurityContext SecurityContext securityContext
            , @AuthenticationPrincipal UserDetails userDetails) {
        return userService.findById(id).map(user -> {
            model.addAttribute("user", user);
            model.addAttribute("roles", Role.values());
            model.addAttribute("positions", Position.values());
            return "user/user";
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/registration")
    public String registration(Model model, UserCreateEditDto user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("positions", Position.values());
        return "user/registration";
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public String create(@ModelAttribute UserCreateEditDto user) {
//        return "redirect:/users/" + userService.create(user).getId();
//    }

    //    @PutMapping("/{id}")
//    @PostMapping("/{id}/update")
//    public String update(@PathVariable("id") Long id, @ModelAttribute UserCreateEditDto user) {
//        return userService.update(id, user)
//                .map(it -> "redirect:/users/{id}")
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

    //    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }

}
