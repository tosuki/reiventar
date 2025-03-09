package com.reiventar.server.core.usecase;

import com.reiventar.server.core.errors.AuthError;
import com.reiventar.server.core.errors.CoreError;
import com.reiventar.server.core.model.User;
import com.reiventar.server.core.ports.PassportEncoder;
import com.reiventar.server.core.ports.UserRepository;
import com.reiventar.server.core.ports.EncryptionProvider;

public class AuthUsecase {
    private final UserRepository userRepository;
    private final PassportEncoder passportEncoder;
    private final EncryptionProvider encryptionProvider;

    public AuthUsecase(UserRepository userRepository, PassportEncoder passportEncoder, EncryptionProvider encryptionProvider) {
        this.userRepository = userRepository;
        this.passportEncoder = passportEncoder;
        this.encryptionProvider = encryptionProvider;
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
