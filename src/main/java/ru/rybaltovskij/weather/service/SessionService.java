package ru.rybaltovskij.weather.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybaltovskij.weather.model.Session;
import ru.rybaltovskij.weather.model.User;
import ru.rybaltovskij.weather.repository.SessionRepository;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public UUID createNewSession(User user) {
        sessionRepository.deleteByUserId(user);
        UUID uuid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        Session session = Session.builder()
                .id(uuid)
                .userId(user)
                .expiresAt(now)
                .build();
        sessionRepository.save(session);
        return session.getId();
    }

    public Optional<Session> getSessionById(UUID sessionId) {
        return sessionRepository.findById(sessionId);
    }

    public String getUserBySession(UUID sessionId) {
        return sessionRepository.findUserLoginBySessionId(sessionId);
    }

    public void deleteSession(UUID sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}
