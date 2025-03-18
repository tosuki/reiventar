package com.reinventar.server.core.model;

public class UserRole {
    public final long user_id;
    public final Permissions permission;

    public UserRole(long user_id, Permissions permission) {
        this.user_id = user_id;
        this.permission = permission;
    }
}

