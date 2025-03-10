package com.reinventar.server.domain.provider;

import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.ds.PGSimpleDataSource;

import com.reinventar.server.core.errors.CriticalError;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class PostgresDatabaseProvider {
    private HikariDataSource database = null;

    private final String hostname;
    private final int port;
    private final String username;
    private final String password;
    private final String databaseName;

    public PostgresDatabaseProvider(
        String hostname,
        int port,
        String username,
        String password,
        String databaseName
    ) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
    }

    private PGSimpleDataSource createPostgresDataSource(
        String hostname,
        int port,
        String username,
        String password,
        String databaseName
    ) {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();

        pgSimpleDataSource.setServerNames(new String[]{ hostname });
        pgSimpleDataSource.setPortNumbers(new int[]{ port });
        pgSimpleDataSource.setPassword(password);
        pgSimpleDataSource.setUser(username);
        pgSimpleDataSource.setDatabaseName(databaseName);

        return pgSimpleDataSource;
    }

    private HikariConfig createHikariConfig(PGSimpleDataSource postgresDataSource) {
        HikariConfig config = new HikariConfig();

        config.setDataSource(postgresDataSource);
        config.setMaximumPoolSize(2);//because we are in a development environment, change this if its in production
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(600000);
        config.setConnectionTimeout(30000);

        return config;
    }

    public void connect(
        String hostname,
        int port,
        String username,
        String password,
        String databaseName
    ) {
        PGSimpleDataSource pgDataSource = this.createPostgresDataSource(
            hostname,
            port,
            username,
            password,
            databaseName
        );

        this.database = new HikariDataSource(this.createHikariConfig(pgDataSource));
    }

    public void connect() {
        this.connect(hostname, port, username, password, databaseName);
    }

    public Connection getConnection() {
        if (this.database == null) {
            throw new CriticalError.MissingDataSource();
        }

        try {
            return this.database.getConnection();
        } catch (SQLException exception) {
            throw new CriticalError.DatabaseSQLError(exception);
        } 
    }

    public void close() {
        this.database.close();
    }
}
