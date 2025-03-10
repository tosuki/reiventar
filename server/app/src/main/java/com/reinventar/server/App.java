package com.reinventar.server;

import com.reinventar.server.core.Logger;
import com.reinventar.server.core.errors.CriticalError;
import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.ports.UserRepository;
import com.reinventar.server.domain.provider.PostgresDatabaseProvider;
import com.reinventar.server.domain.repository.UserPostgresRepositoryImpl;

public class App {
    public static void main(String[] args) {
        PostgresDatabaseProvider databaseProvider = new PostgresDatabaseProvider(
            "localhost",
            5432,
            "postgres",
            "root",
            "reiventar"
        );

        try {
            databaseProvider.connect();

            UserPostgresRepositoryImpl userRepository = new UserPostgresRepositoryImpl(databaseProvider);

            userRepository.initialize();
            databaseProvider.close();
        } catch (CriticalError error) {
            Logger.error(error);
        }
    }
}
