package com.reiventar.server.core.error;

public abstract class ReiventarError extends Error {
    public ReiventarError(String level, String message) {
        super("[" + level + "] " + message);
    }
}
