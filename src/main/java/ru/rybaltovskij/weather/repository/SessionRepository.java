package ru.rybaltovskij.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rybaltovskij.weather.model.Session;
import ru.rybaltovskij.weather.model.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM Session s WHERE s.expiresAt < :now")
    int deleteByExpiresAtBefore(@Param("now") LocalDateTime now);

    @Query("SELECT s.userId FROM Session s WHERE s.id = :sessionId")
    User findUserBySessionId(@Param("sessionId") UUID sessionId);

    void deleteByUserId(User user);
}
