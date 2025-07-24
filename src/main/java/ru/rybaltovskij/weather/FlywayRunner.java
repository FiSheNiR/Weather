package ru.rybaltovskij.weather;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@Slf4j
public class FlywayRunner {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.rybaltovskij.weather");
        Flyway flyway = context.getBean(Flyway.class);
    }
}


