package ru.rybaltovskij.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ru.rybaltovskij.weather.model.User;
import ru.rybaltovskij.weather.service.SessionService;

import java.util.UUID;

@Controller
public class HomeController {
    @Autowired
    SessionService sessionService;

    @GetMapping
    public String home(@CookieValue(name = "SESSION_ID", required = false) String sessionId, Model model) {
        String username = String.
                valueOf(sessionService.
                        getUserBySession(UUID.fromString(sessionId)));
        model.addAttribute("username", username);
        return "index";
    }
}
