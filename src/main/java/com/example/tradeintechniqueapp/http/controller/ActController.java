package com.example.tradeintechniqueapp.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/act")
public class ActController {

    @GetMapping("/editAct/{id}")
    public ModelAndView editAct(@PathVariable Long id, ModelAndView modelAndView){
        modelAndView.setViewName("forAllUsers/editAct");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

}
