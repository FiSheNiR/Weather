package ru.rybaltovskij.weather.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rybaltovskij.weather.dto.UserRegisterDto;
import ru.rybaltovskij.weather.service.UserService;

@Controller
@Slf4j
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String signUp(@ModelAttribute("newUser") UserRegisterDto userRegisterDto) {
        return "sign-up";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("newUser") UserRegisterDto userRegisterDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sign-up-with-errors";
        }
        userService.register(userRegisterDto);
        return "redirect:/signIn";
    }
}
