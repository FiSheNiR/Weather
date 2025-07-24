package ru.rybaltovskij.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybaltovskij.weather.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
