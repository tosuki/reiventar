package com.reinventar.server.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.reinventar.server.core.errors.CoreError;
import com.reinventar.server.core.errors.CriticalError;
import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.model.User;
import com.reinventar.server.core.ports.UserRepository;
import com.reinventar.server.domain.provider.PostgresDatabaseProvider;

public class UserPostgresRepositoryImpl implements UserRepository {
    private final PostgresDatabaseProvider databaseProvider;

    public UserPostgresRepositoryImpl(PostgresDatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    public void initialize() {
        Connection connection = this.databaseProvider.getConnection();
     
        try {
            PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS users (id BIGSERIAL, name TEXT NOT NULL, password TEXT NOT NULL, permission INTEGER NOT NULL, created_at TIMESTAMP DEFAULT current_timestamp, updated_at TIMESTAMP DEFAULT current_timestamp);"
            );

            statement.execute();
        } catch (SQLException exception) {
            throw new CriticalError.DatabaseSQLError(exception);
        }
    }

    @Override
    public User create(String name, String password, Permissions permissions) {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public User get(long id) {
        try {
            Connection connection = this.databaseProvider.getConnection();


            return null;
        } catch (CoreError error) {
            throw error;
        }  
    }

    @Override
    public User get(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void delete(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
