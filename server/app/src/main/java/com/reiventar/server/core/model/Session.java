package com.reiventar.server.core.model;

public class Session {
    public String uuid;
    public String username;
    public long createdAt;
    public long expiresAt;
    public long issuedAt;

    public Session(
        String uuid,
        String username,
        long createdAt,
        long expiresAt,
        long issuedAt
    ) {
        this.uuid = uuid;
        this.username = username;
        this.createdAt = createdAt;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }
}
