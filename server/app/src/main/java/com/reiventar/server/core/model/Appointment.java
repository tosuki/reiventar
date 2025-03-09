package com.reiventar.server.core.model;

public class Appointment {
    public final long id;
    public final long createdBy;
    public final long date;
    public final long kind;
    public final long professional;
    public final long client;
    public final long createdAt;

    public Appointment(
        long id,
        long createdBy,
        long date,
        long kind,
        long client,
        long professional,
        long createdAt
    ) {
        this.id = id;
        this.createdBy = createdAt;
        this.date = date;
        this.kind = kind;
        this.professional = professional;
        this.client = client;
        this.createdAt = createdAt;
    }
}
