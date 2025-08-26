package ru.rybaltovskij.weather.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rybaltovskij.weather.dto.UserRegisterDto;
import ru.rybaltovskij.weather.exception.NotUniqueUserException;
import ru.rybaltovskij.weather.exception.UnavailablePasswordException;
import ru.rybaltovskij.weather.exception.WrongPasswordException;
import ru.rybaltovskij.weather.service.UserService;
import ru.rybaltovskij.weather.util.ValidationUtil;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/signUp")
public class SignUpController {

    private final UserService userService;
    private final ValidationUtil validationUtil;

    @GetMapping
    public String signUp(@ModelAttribute("newUser") UserRegisterDto userRegisterDto) {
        return "sign-up";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("newUser") UserRegisterDto userRegisterDto, Model model) {
        try {
            validationUtil.validateUserRegisterDto(userRegisterDto);
            userService.register(userRegisterDto);
            return "redirect:/signIn";
        } catch (UnavailablePasswordException | WrongPasswordException | NotUniqueUserException e) {
            log.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "sign-up-with-errors";
        }
    }
}
