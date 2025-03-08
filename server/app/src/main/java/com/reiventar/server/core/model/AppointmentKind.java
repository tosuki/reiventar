package com.reiventar.server.core.model;

public class AppointmentKind {
    public final long id;
    public final String name;
    public final String author;
    public final long createdAt;
    public long updatedAt;

    public AppointmentKind(
        long id,
        String name,
        String author,
        long createdAt,
        long updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
