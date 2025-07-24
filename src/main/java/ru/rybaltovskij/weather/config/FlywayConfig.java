package ru.rybaltovskij.weather.config;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class FlywayConfig {

    private final DataSource dataSource;

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration") // где лежат миграции
                .load();

        flyway.migrate();// применяем миграции при старте
        return flyway;
    }
}
