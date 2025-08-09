package ru.rybaltovskij.weather.exception;

public class NotUniqueUserException extends RuntimeException {
    public NotUniqueUserException(String message) {
        super(message);
    }

    public NotUniqueUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
