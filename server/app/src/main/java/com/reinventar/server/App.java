package com.reinventar.server;

import com.reinventar.server.core.Logger;
import com.reinventar.server.core.errors.CoreError;
import com.reinventar.server.core.model.User;
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

            User user = userRepository.get(1);
            Logger.info("%s\n", user.permission);

            databaseProvider.close();
        } catch (CoreError error) {
            Logger.error(error);
        }
    }
}
