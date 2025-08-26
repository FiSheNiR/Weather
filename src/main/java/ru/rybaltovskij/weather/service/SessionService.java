package ru.rybaltovskij.weather.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rybaltovskij.weather.model.Session;
import ru.rybaltovskij.weather.model.User;
import ru.rybaltovskij.weather.repository.SessionRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    @Transactional
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

    public User getUserBySession(UUID sessionId) {
        return sessionRepository.findUserBySessionId(sessionId);
    }

    @Transactional
    public void deleteSession(UUID sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}
