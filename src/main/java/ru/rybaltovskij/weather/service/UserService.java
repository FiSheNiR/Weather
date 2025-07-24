package ru.rybaltovskij.weather.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rybaltovskij.weather.dto.UserRegisterDto;
import ru.rybaltovskij.weather.dto.UserSignInRequestDto;
import ru.rybaltovskij.weather.mapper.UserMapper;
import ru.rybaltovskij.weather.model.User;
import ru.rybaltovskij.weather.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionService sessionService;


    public void register(UserRegisterDto userRegisterDto) {
        User user = userMapper.toEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Optional<UUID> signIn(UserSignInRequestDto userSignInRequestDto) {
        String username = userSignInRequestDto.getUsername();
        String password = userSignInRequestDto.getPassword();
        User user = findUserByLogin(username);
        if (passwordEncoder.matches(password, user.getPassword())) {
            UUID uuid = sessionService.createNewSession(user);
            return Optional.ofNullable(uuid);
        }
        return Optional.empty();
    }

    private User findUserByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow();
    }
}
