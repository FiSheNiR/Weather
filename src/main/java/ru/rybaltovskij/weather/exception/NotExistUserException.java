package ru.rybaltovskij.weather.exception;

public class NotExistUserException extends RuntimeException {
    public NotExistUserException(String message) {
        super(message);
    }
}
