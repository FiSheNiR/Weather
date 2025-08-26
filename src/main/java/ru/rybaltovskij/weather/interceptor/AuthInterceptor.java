package ru.rybaltovskij.weather.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;
import ru.rybaltovskij.weather.context.RequestContextHolder;
import ru.rybaltovskij.weather.model.Session;
import ru.rybaltovskij.weather.service.SessionService;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cookie = WebUtils.getCookie(request, "SESSION_ID");
        if (cookie != null) {
            String sessionId = cookie.getValue();
            Optional<Session> session;
            try {
                session = sessionService.getSessionById(UUID.fromString(sessionId));
            } catch (IllegalArgumentException e) {
                session = Optional.empty();
            }
            if (session.isPresent()) {
                Session currentSession = session.get();
                RequestContextHolder.setSessionId(currentSession.getId());
                return true;
            }
        }
        RequestContextHolder.clear();
        response.sendRedirect("/signIn");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContextHolder.clear();
    }
}
