package com.reiventar.server.core.model;

public class AppointmentKind {
    public final long id;
    public final long createdBy;
    public final String name;

    public AppointmentKind(long id, long createdBy, String name) {
        this.id = id;
        this.createdBy = createdBy;
        this.name = name;
    }
}
