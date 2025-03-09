package com.reiventar.server.core.model;

public class Session {
    public final long id;
    public final String name;
    public Permissions permission;
    public final long createdAt;
    public final long updatedAt;
    public final long expiresAt;
    public final long issuedAt;

    public Session(
        long id,
        String name,
        Permissions permission,
        long createdAt,
        long updatedAt,
        long expiresAt,
        long issuedAt
    ) {
        this.id = id;
        this.name = name;
        this.permission = permission;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expiresAt = expiresAt;
        this.issuedAt = issuedAt;
    }
}
