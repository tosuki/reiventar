package com.reinventar.server.core.model;

public class Customer {
    public final long id;
    public final String name;
    public final long createdAt;
    public final long updatedAt;

    public Customer(
        long id,
        String name,
        long createdAt,
        long updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
