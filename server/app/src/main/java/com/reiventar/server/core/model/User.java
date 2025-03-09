package com.reiventar.server.core.model;

public class User {
    public final long id;
    public final String name;
    public final String password;
    public final Permissions permission;
    public final long createdAt;
    public final long updatedAt;

    public User(
        long id,
        String name,
        String password,
        Permissions permission,
        long createdAt,
        long updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.permission = permission;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
