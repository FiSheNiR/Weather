package ru.rybaltovskij.weather.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rybaltovskij.weather.dto.UserRegisterDto;
import ru.rybaltovskij.weather.dto.UserSignInRequestDto;
import ru.rybaltovskij.weather.exception.NotExistUserException;
import ru.rybaltovskij.weather.exception.NotUniqueUserException;
import ru.rybaltovskij.weather.exception.WrongPasswordException;
import ru.rybaltovskij.weather.mapper.UserMapper;
import ru.rybaltovskij.weather.model.User;
import ru.rybaltovskij.weather.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SessionService sessionService;

    @Transactional
    public void register(UserRegisterDto userRegisterDto) {
        User user = userMapper.toEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new NotUniqueUserException("not unique user", e);
        }
    }

    public UUID signIn(UserSignInRequestDto userSignInRequestDto) {
        String username = userSignInRequestDto.getUsername();
        String password = userSignInRequestDto.getPassword();
        User user = findUserByLogin(username);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return sessionService.createNewSession(user);
        } else {
            throw new WrongPasswordException("wrong password");
        }
    }

    private User findUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new NotExistUserException("User dont exist"));
    }
}
