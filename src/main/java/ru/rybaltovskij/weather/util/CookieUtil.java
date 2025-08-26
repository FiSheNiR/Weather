package ru.rybaltovskij.weather.util;

import jakarta.servlet.http.Cookie;

import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    public Cookie getEmptyCookie() {
        Cookie emptyCookie = new Cookie("SESSION_ID", null);
        emptyCookie.setPath("/");
        emptyCookie.setMaxAge(0);
        return emptyCookie;
    }
}
