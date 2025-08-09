package ru.rybaltovskij.weather.advice;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;
import ru.rybaltovskij.weather.context.RequestContextHolder;
import ru.rybaltovskij.weather.model.User;
import ru.rybaltovskij.weather.service.SessionService;

import java.util.UUID;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelControllerAdvice {

    private final SessionService sessionService;

    @ModelAttribute
    public void addUsernameToModel(WebRequest webRequest, Model model) {
        UUID sessionId = RequestContextHolder.getSessionId();

        String username = null;

        if (sessionId != null) {
            User user = sessionService.getUserBySession(sessionId);
            if (user != null) {
                username = user.getLogin();
            }
        }

        model.addAttribute("username", username);
    }
}
