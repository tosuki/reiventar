package com.reinventar.server.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.reinventar.server.core.errors.CriticalError;
import com.reinventar.server.core.errors.CustomerError;
import com.reinventar.server.core.model.Customer;
import com.reinventar.server.core.ports.CustomerRepository;
import com.reinventar.server.domain.provider.PostgresDatabaseProvider;

/**
 * Table structure
 * id unique serial id
 * name unique name
 * created_at and updated_at timestamp
 */
public class CustomerPostgresRepositoryImpl implements CustomerRepository {
    private final PostgresDatabaseProvider databaseProvider;

    public CustomerPostgresRepositoryImpl(PostgresDatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    @Override
    public void initialize() {
        try {
            Connection connection = databaseProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS customers (id BIGSERIAL, name TEXT NOT NULL UNIQUE, created_at TIMESTAMP DEFAULT current_timestamp, updated_at TIMESTAMP DEFAULT current_timestamp);"
            );

            statement.execute();
        } catch (SQLException exception) {
            throw new CriticalError.DatabaseSQLError(exception);
        }
    }

    @Override
    public Customer create(String name) {
        try {
            Connection connection = databaseProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO customers (name) VALUES (?) RETURNING *;"
            );

            statement.setString(1, name);
            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                throw new CriticalError.UnhandledError("Empty rows on insert query returning", "customer repository - create");
            }
            
            return new Customer(
                result.getLong("id"),
                result.getString("name"),
                result.getTimestamp("created_at").getTime(),
                result.getTimestamp("updated_at").getTime()
            );
        } catch (SQLException exception) {
            if (exception.getSQLState().equals("23505")) {
                throw new CustomerError.OccupiedName(name);
            }

            throw new CriticalError.DatabaseSQLError(exception); 
        }
    }

    @Override
    public Customer get(String name) {
        try {
            Connection connection = databaseProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM customers WHERE name = ?;"
            );

            statement.setString(1, name);
            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                return null;
            }
            
            return new Customer(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getTimestamp("created_at").getTime(), 
                    result.getTimestamp("updated_at").getTime()
            );
        } catch (SQLException exception) {
            throw new CriticalError.DatabaseSQLError(exception);
        }
    }

    @Override
    public Customer get(long id) {
        try {
            Connection connection = databaseProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM customers WHERE id = ?;"
            );

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return new Customer(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getTimestamp("created_at").getTime(),
                resultSet.getTimestamp("updated_at").getTime()
            );
        } catch (SQLException exception) {
            throw new CriticalError.DatabaseSQLError(exception);
        }
    }

    @Override
    public void delete(String name) {
        try {
            Connection connection = databaseProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "DELETE VALUES FROM customers WHERE name = ?;"
            );

            statement.setString(0, name);
            statement.execute();
        } catch (SQLException exception) {
            throw new CriticalError.DatabaseSQLError(exception);
        }
    }

    @Override
    public void delete(long id) {
        try {
            Connection connection = databaseProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "DELETE VALUES FROM customers WHERE id = ?;"
            );

            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException exception) {
            throw new CriticalError.DatabaseSQLError(exception);
        } 
    }
}
