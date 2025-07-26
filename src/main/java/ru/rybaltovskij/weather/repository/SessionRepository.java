package ru.rybaltovskij.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rybaltovskij.weather.model.Session;
import ru.rybaltovskij.weather.model.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM Session s WHERE s.userId = :user")
    void deleteByUserId(@Param("user") User user);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM Session s WHERE s.expiresAt < :now")
    int deleteByExpiresAtBefore(@Param("now") LocalDateTime now);

    @Query("SELECT s.userId.login FROM Session s WHERE s.id = :sessionId")
    String findUserLoginBySessionId(@Param("sessionId") UUID sessionId);
}
