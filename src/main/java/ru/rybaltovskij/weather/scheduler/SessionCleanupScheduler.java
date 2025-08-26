package ru.rybaltovskij.weather.scheduler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rybaltovskij.weather.repository.SessionRepository;

import java.time.LocalDateTime;


@Slf4j
@RequiredArgsConstructor
@Component
public class SessionCleanupScheduler {

    private final SessionRepository sessionRepository;

    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    public void cleanupExpiredSessions() {
        LocalDateTime now = LocalDateTime.now();
        int deleted = sessionRepository.deleteByExpiresAtBefore(now);
        log.info("Успешно удалено {} просроченных сессий", deleted);
    }
}
