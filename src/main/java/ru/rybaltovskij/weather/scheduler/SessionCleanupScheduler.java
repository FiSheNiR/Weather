package ru.rybaltovskij.weather.scheduler;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rybaltovskij.weather.repository.SessionRepository;

import java.time.LocalDateTime;


@Slf4j
@Component
public class SessionCleanupScheduler {

    @Autowired
    private SessionRepository sessionRepository;

    @Scheduled(fixedRate = 3600000)
    @Transactional
    public void cleanupExpiredSessions() {
        log.info("Запуск очистки сессий...");
        LocalDateTime now = LocalDateTime.now();
        int deleted = sessionRepository.deleteByExpiresAtBefore(now);
        if (deleted > 0) {
            log.info("Успешно удалено {} просроченных сессий", deleted);
        } else {
            log.debug("Нет просроченных сессий для удаления");
        }
    }
}
