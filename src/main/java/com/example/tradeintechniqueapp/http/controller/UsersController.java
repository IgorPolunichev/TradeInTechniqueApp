package com.example.tradeintechniqueapp.http.controller;

import com.example.tradeintechniqueapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UsersController {
    private final UserService userService;

    @GetMapping("/newAct")
    public String findAllMachines() {
        return "forAllUsers/newAct";
    }
//    @GetMapping("/listCompanies")
//    public String findAllCompanies() {
//        return "user/companies";
//    }
    @GetMapping("/listActs")
    public String findAllParts() {
        return "forAllUsers/allActs";
    }

    @GetMapping()
    public String findUserId(){
        return "forAllUsers/user";
    }



}
