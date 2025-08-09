package ru.rybaltovskij.weather.exception;

public class UnavailablePasswordException extends RuntimeException {
    public UnavailablePasswordException(String message) {
        super(message);
    }
}
