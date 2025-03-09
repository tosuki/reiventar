package com.reiventar.server.core.errors;

public abstract class CoreError extends Error {
    public CoreError(String layer, String message) {
        super("[" + layer + "] " + message);
    }
}
