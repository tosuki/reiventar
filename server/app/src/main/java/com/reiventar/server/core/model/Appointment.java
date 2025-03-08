package com.reiventar.server.core.model;

public class Appointment {
    public final long id;
    public final String author;
    public final String kind;
    public final String professional;

    public Appointment(
        long id,
        String author,
        String kind,
        String professional
    ) {
        this.id = id;
        this.author = author;
        this.kind = kind;
        this.professional = professional;
    }
}
