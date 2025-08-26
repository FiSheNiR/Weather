package ru.rybaltovskij.weather.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rybaltovskij.weather.service.SessionService;
import ru.rybaltovskij.weather.service.UserService;
import ru.rybaltovskij.weather.util.CookieUtil;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signOut")
public class SignOutController {

    private final SessionService sessionService;
    private final CookieUtil cookieUtil;

    @GetMapping
    public String signOut(@CookieValue(name = "SESSION_ID", required = false) String sessionId, HttpServletResponse httpServletResponse) {
        sessionService.deleteSession(UUID.fromString(sessionId));
        httpServletResponse.addCookie(cookieUtil.getEmptyCookie());
        return "redirect:/signIn";
    }
}
