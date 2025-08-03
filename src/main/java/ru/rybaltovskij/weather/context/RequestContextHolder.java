package ru.rybaltovskij.weather.context;

import java.util.UUID;

public class RequestContextHolder {
    private static final ThreadLocal<UUID> context = new ThreadLocal<>();

    public static void setSessionId(UUID sessionId) {
        context.set(sessionId);
    }

    public static UUID getSessionId() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}
