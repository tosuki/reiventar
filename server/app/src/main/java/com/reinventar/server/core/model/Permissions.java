package com.reinventar.server.core.model;

public enum Permissions {
    ADMINISTRATOR(1), 
    MANAGER(2), 
    PROFESSIONAL(3);

    private final int permissionLevel;

    Permissions(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public static Permissions fromInt(int value) {
        for (Permissions p : values()) {
            if (p.getPermissionLevel() == value) {
                return p;
            }
        }
        
        throw new IllegalArgumentException();
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }
}
