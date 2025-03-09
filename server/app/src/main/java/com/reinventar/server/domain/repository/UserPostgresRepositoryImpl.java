package com.reinventar.server.domain.repository;

import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.model.User;
import com.reinventar.server.core.ports.UserRepository;
import com.reinventar.server.domain.provider.PostgresDatabaseProvider;

public class UserPostgresRepositoryImpl implements UserRepository {
    private final PostgresDatabaseProvider database;

    public UserPostgresRepositoryImpl(PostgresDatabaseProvider database) {
        this.database = database;
    }

    @Override
    public User create(String name, String password, Permissions permissions) {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public User get(long id) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
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
