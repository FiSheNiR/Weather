package ru.rybaltovskij.weather.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rybaltovskij.weather.service.SessionService;
import ru.rybaltovskij.weather.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/signOut")
public class SignOutController {

    @Autowired
    private SessionService sessionService;

    @GetMapping
    public String signOut(@CookieValue(name = "SESSION_ID", required = false) String sessionId, HttpServletResponse httpServletResponse) {
        sessionService.deleteSession(UUID.fromString(sessionId));
        Cookie clearCookie = new Cookie("SESSION_ID", null);
        clearCookie.setPath("/");
        clearCookie.setMaxAge(0);
        httpServletResponse.addCookie(clearCookie);
        return "redirect:/signIn";
    }
}
