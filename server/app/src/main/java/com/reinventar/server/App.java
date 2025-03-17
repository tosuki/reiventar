package com.reinventar.server;

import com.reinventar.server.core.Logger;
import com.reinventar.server.core.errors.CoreError;
import com.reinventar.server.core.model.Customer;
import com.reinventar.server.core.ports.CustomerRepository;
import com.reinventar.server.core.ports.EncryptionProvider;
import com.reinventar.server.core.ports.PassportEncoder;
import com.reinventar.server.core.ports.UserRepository;
import com.reinventar.server.core.usecase.AuthUsecase;
import com.reinventar.server.domain.factory.PortsFactory;
import com.reinventar.server.domain.factory.RepositoryFactory;
import com.reinventar.server.domain.provider.PostgresDatabaseProvider;

public class App {
    public static void main(String[] args) {
        try {
            PostgresDatabaseProvider databaseProvider = new PostgresDatabaseProvider(
                "localhost",
                5432,
                "postgres",
                "root",
                "reiventar"
            );

            databaseProvider.connect();

            EncryptionProvider encryptionProvider = PortsFactory.newEncryptionProvider();
            PassportEncoder passportEncoder = PortsFactory.newPassportEncoder("aa");
            UserRepository userRepository = RepositoryFactory.newUserRepository(databaseProvider);
            CustomerRepository customerRepository = RepositoryFactory.newCustomerRepository(databaseProvider);

            customerRepository.initialize();
            
            Customer customer = customerRepository.get("Carloss");

            if (customer == null) {
                Logger.error("It doesnt exist");
            }
        } catch (CoreError error) {
            Logger.error(error);
        }
    }
}
