package ru.rybaltovskij.weather.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rybaltovskij.weather.dto.UserSignInRequestDto;
import ru.rybaltovskij.weather.exception.NotExistUserException;
import ru.rybaltovskij.weather.exception.WrongPasswordException;
import ru.rybaltovskij.weather.service.UserService;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/signIn")
public class SignInController {

    private final UserService userService;

    @GetMapping
    public String singIn(@ModelAttribute("user") UserSignInRequestDto user) {
        return "sign-in";
    }

    @PostMapping
    public String signIn(@ModelAttribute("user") UserSignInRequestDto user, HttpServletResponse response) {
        try {
            UUID sessionId = userService.signIn(user);
            Cookie sessionCookie = new Cookie("SESSION_ID", sessionId.toString());
            sessionCookie.setPath("/");
            sessionCookie.setMaxAge(60*60);
            response.addCookie(sessionCookie);
            return "redirect:/";
        } catch (WrongPasswordException | NotExistUserException e) {
            log.error(e.getMessage(), e);
            return "sign-in-with-errors";
        }
    }
}
