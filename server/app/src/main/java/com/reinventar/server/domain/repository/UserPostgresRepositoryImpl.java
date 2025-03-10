package com.reinventar.server.domain.repository;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.reinventar.server.core.Logger;
import com.reinventar.server.core.errors.AuthError;
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
                "CREATE TABLE IF NOT EXISTS users (id BIGSERIAL, name TEXT NOT NULL UNIQUE, password TEXT NOT NULL, permission INTEGER NOT NULL, created_at TIMESTAMP DEFAULT current_timestamp, updated_at TIMESTAMP DEFAULT current_timestamp);"
            );

            statement.execute();
        } catch (SQLException exception) {
            throw new CriticalError.DatabaseSQLError(exception);
        }
    }

    @Override
    public User create(String name, String password, Permissions permissions) {
        try {
            Connection connection = databaseProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, password, permission) VALUES (?, ?, ?) RETURNING *;");

            statement.setString(1, name);
            statement.setString(2, password);
            statement.setInt(3, permissions.getPermissionLevel());

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new CriticalError.UnhandledError("Empty rows on insert query (users)");
            }

            User user = new User(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("password"),
                Permissions.fromInt(resultSet.getInt("permission")),
                resultSet.getTimestamp("created_at").getTime(),
                resultSet.getTimestamp("created_at").getTime()
            );
            
            return user;
        } catch (SQLException exception) {
            if (exception.getSQLState().equals("23505")) {
                throw new AuthError.NameOccupied(name);
            }

            throw new CriticalError.DatabaseSQLError(exception);
        }
    }

    @Override
    public User get(long id) {
        try {
            Connection connection = this.databaseProvider.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?;");
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                return null;
            }

            long user_id = result.getLong("id");

            Logger.info("%d", user_id);
            
            return null;
        } catch (SQLException exception) {
            throw new CriticalError.DatabaseSQLError(exception);
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
