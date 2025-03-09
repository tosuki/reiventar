package com.reinventar.server.domain.provider;

import com.zaxxer.hikari.HikariDataSource;

public class PostgresDatabaseProvider {
    private final String connection;
    private final HikariDataSource dataSource = null;

    public PostgresDatabaseProvider(String connection) {
        this.connection = connection;
    }

    public void connect(String connection) {
        
    }

    public void connect() {
        this.connect(this.connection);
    }
}
