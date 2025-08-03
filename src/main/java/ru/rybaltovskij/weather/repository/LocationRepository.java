package ru.rybaltovskij.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rybaltovskij.weather.model.Location;
import ru.rybaltovskij.weather.model.User;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAllByUserId(User userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Location l WHERE l.userId = :user " +
            "AND ABS(l.latitude - :latitude) < 0.002 " +
            "AND ABS(l.longitude - :longitude) < 0.002")
    void deleteByUserUsernameAndCoordinates(
            @Param("user") User user,
            @Param("latitude") BigDecimal latitude,
            @Param("longitude") BigDecimal longitude
    );
}
