package ru.rybaltovskij.weather.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybaltovskij.weather.model.Session;
import ru.rybaltovskij.weather.model.User;
import ru.rybaltovskij.weather.repository.SessionRepository;


import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public UUID createNewSession(User user) {
        UUID uuid = UUID.randomUUID();
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + 3600000);
        Session session = Session.builder()
                .id(uuid)
                .userId(user)
                .expiresAt(expireTime)
                .build();
        sessionRepository.save(session);
        return session.getId();
    }
}
