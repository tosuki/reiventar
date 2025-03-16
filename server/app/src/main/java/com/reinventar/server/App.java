package com.reinventar.server;

import com.reinventar.server.core.Logger;
import com.reinventar.server.core.errors.CoreError;
import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.ports.EncryptionProvider;
import com.reinventar.server.core.ports.PassportEncoder;
import com.reinventar.server.core.ports.UserRepository;
import com.reinventar.server.core.usecase.AuthUsecase;
import com.reinventar.server.domain.provider.BCryptEncryptionProvider;
import com.reinventar.server.domain.provider.PassportEncoderImpl;
import com.reinventar.server.domain.provider.PostgresDatabaseProvider;
import com.reinventar.server.domain.repository.UserPostgresRepositoryImpl;

public class App {
    public static void main(String[] args) {
        try {
            EncryptionProvider encryptionProvider = new BCryptEncryptionProvider();
            PassportEncoder passportEncoder = new PassportEncoderImpl("null".getBytes());
            
            PostgresDatabaseProvider postgresDatabaseProvider = new PostgresDatabaseProvider(
                "localhost", 5432, "postgres", "root", "reiventar"
            );

            postgresDatabaseProvider.connect();
            UserRepository userRepository = new UserPostgresRepositoryImpl(postgresDatabaseProvider);
            
            AuthUsecase authUsecase = new AuthUsecase(userRepository, passportEncoder, encryptionProvider);

            String passport = authUsecase.register("hello", "123", Permissions.ADMINISTRATOR);

            System.out.println("The passport is " + passport);
        } catch (CoreError error) {
            Logger.error(error);
        }
    }
}
