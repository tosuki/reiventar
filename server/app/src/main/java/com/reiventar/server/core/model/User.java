package com.reiventar.server.core.model;

public class User {
    public final String uuid;
    public final String username;
    public final int roles;
    public final String password;
    public final long createdAt;
    public long updatedAt;

    public User(
        String uuid,
        String username,
        String password,
        int roles,
        long createdAt,
        long updatedAt
    ) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
