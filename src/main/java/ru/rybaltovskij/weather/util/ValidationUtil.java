package ru.rybaltovskij.weather.util;

import org.springframework.stereotype.Component;
import ru.rybaltovskij.weather.dto.UserRegisterDto;
import ru.rybaltovskij.weather.exception.UnavailablePasswordException;
import ru.rybaltovskij.weather.exception.UnavailableUsernameException;

@Component
public class ValidationUtil {

    public void validateUserRegisterDto(UserRegisterDto userRegisterDto) {
        validateUsername(userRegisterDto.getUsername());
        validatePassword(userRegisterDto.getPassword(), userRegisterDto.getRepeatPassword());
    }

    private void validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new UnavailableUsernameException("Username is empty");
        } else if (username.length() < 3 || username.length() > 50) {
            throw new UnavailableUsernameException("Username length must be between 3 and 50");
        }
    }
    private void validatePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new UnavailablePasswordException("Passwords do not match");
        }
        if (password.length() < 4 || password.length() > 16) {
            throw new UnavailablePasswordException("Password length must be between 4 and 50");
        }
    }
}
