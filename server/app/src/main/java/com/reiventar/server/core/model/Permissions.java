package com.reiventar.server.core.model;

public enum Permissions {
    ADMINISTRATOR(1), 
    MANAGER(2), 
    PROFESSIONAL(3);

    private final int permissionLevel;

    Permissions(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }
}
