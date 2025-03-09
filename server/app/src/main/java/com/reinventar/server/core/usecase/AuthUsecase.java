package com.reinventar.server.core.usecase;

import com.reinventar.server.core.errors.AuthError;
import com.reinventar.server.core.errors.CoreError;
import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.model.User;
import com.reinventar.server.core.ports.EncryptionProvider;
import com.reinventar.server.core.ports.PassportEncoder;
import com.reinventar.server.core.ports.UserRepository;

public class AuthUsecase {
    private final UserRepository userRepository;
    private final PassportEncoder passportEncoder;
    private final EncryptionProvider encryptionProvider;

    public AuthUsecase(UserRepository userRepository, PassportEncoder passportEncoder, EncryptionProvider encryptionProvider) {
        this.userRepository = userRepository;
        this.passportEncoder = passportEncoder;
        this.encryptionProvider = encryptionProvider;
    }

    public String register(String name, String password, Permissions permission) {
        try {
            User nameOccupied = this.userRepository.get(name);

            if (nameOccupied != null) {
                throw new AuthError.NameOccupied(name);
            }

            String encryptedPassword = this.encryptionProvider.encrypt(password);
            User user = this.userRepository.create(name, encryptedPassword, permission);

            return this.passportEncoder.encode(
                user.id,
                user.name,
                user.permission,
                user.createdAt,
                user.updatedAt
            );
        } catch (CoreError error) {
            throw error;
        }
    }

    public String authenticate(String name, String password) {
        try {
            User user = this.userRepository.get(name);

            if (user == null) {
                throw new AuthError.InvalidCredentials();
            }

            if (!this.encryptionProvider.compare(user.password, password)) {
                throw new AuthError.InvalidCredentials();
            }

            return this.passportEncoder.encode(
                user.id,
                user.name,
                user.permission,
                user.createdAt,
                user.updatedAt
            );
        } catch (CoreError error) {
            throw error;
        }
    }
}
