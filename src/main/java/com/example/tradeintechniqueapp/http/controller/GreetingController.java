package com.example.tradeintechniqueapp.http.controller;

import com.example.tradeintechniqueapp.dto.usersDto.UserReadDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v2")
@SessionAttributes({"user"})
public class GreetingController {
    @GetMapping(value = "/hello")
    public String hello( ModelMap model
            , @ModelAttribute ("userReadDto") UserReadDto userReadDto
            , HttpServletRequest request) {
        model.addAttribute("user", userReadDto);
        return "greeting/hello";
    }
    @GetMapping(value = "/hello/{id}")
    public String hello2( HttpServletRequest request,
                               @RequestParam Integer age,
                               @RequestHeader String accept,
                               @CookieValue("JSESSIONID") String jsessionId,
                               @PathVariable ("id") Integer id) {

        return "greeting/hello";
    }
    @GetMapping(value = "/bye")
    public String bye(HttpServletRequest request, Model model, @SessionAttribute("user") UserReadDto user ) {
        request.getSession().getAttribute("user");

        return "greeting/bye";
    }

}
