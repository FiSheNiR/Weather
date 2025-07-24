package ru.rybaltovskij.weather.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rybaltovskij.weather.dto.UserSignInRequestDto;
import ru.rybaltovskij.weather.service.UserService;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/signIn")
public class SignInController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String singIn(@ModelAttribute("user") UserSignInRequestDto user) {
        return "sign-in";
    }

    @PostMapping
    public String signIn(@ModelAttribute("user") UserSignInRequestDto user,
                         BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "sign-in-with-errors";
        }
        Optional<UUID> sessionId = userService.signIn(user);
        if (sessionId.isPresent()) {
            Cookie sessionCookie = new Cookie("SESSION_ID", sessionId.get().toString());
            sessionCookie.setPath("/");
            response.addCookie(sessionCookie);
        }
        return "redirect:/";
    }
}
