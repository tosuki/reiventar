package com.reinventar.server.domain.repository;

import java.sql.Connection;

import com.reinventar.server.core.errors.CoreError;
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
        //create sql table
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
