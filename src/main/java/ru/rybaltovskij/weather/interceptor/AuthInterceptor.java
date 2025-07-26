package ru.rybaltovskij.weather.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;
import ru.rybaltovskij.weather.model.Session;
import ru.rybaltovskij.weather.service.SessionService;

import java.util.Optional;
import java.util.UUID;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cookie = WebUtils.getCookie(request, "SESSION_ID");
        if (cookie != null) {
            String sessionId = cookie.getValue();
            Optional<Session> session = sessionService.getSessionById(UUID.fromString(sessionId));
            if (session.isPresent()) {
                return true;
            }
        }
        response.sendRedirect("/signIn");
        return false;
    }
}
