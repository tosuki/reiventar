package com.reiventar.server.core.errors;

public abstract class CoreError extends Error {
    public CoreError(String level, String message) {
        super("[" + level + "] " + message);
    }
}
