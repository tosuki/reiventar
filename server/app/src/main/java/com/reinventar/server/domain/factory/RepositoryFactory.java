package com.reinventar.server.domain.factory;

import com.reinventar.server.core.ports.CustomerRepository;
import com.reinventar.server.core.ports.UserRepository;
import com.reinventar.server.domain.provider.PostgresDatabaseProvider;
import com.reinventar.server.domain.repository.CustomerPostgresRepositoryImpl;
import com.reinventar.server.domain.repository.UserPostgresRepositoryImpl;

public class RepositoryFactory {
    public static UserRepository newUserRepository(PostgresDatabaseProvider databaseProvider) {
        return new UserPostgresRepositoryImpl(databaseProvider);
    }

    public static CustomerRepository newCustomerRepository(PostgresDatabaseProvider databaseProvider) {
        return new CustomerPostgresRepositoryImpl(databaseProvider);
    }
}
