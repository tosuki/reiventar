package com.reiventar.server.core.usecase;

import com.reiventar.server.core.error.AuthError;
import com.reiventar.server.core.model.User;
import com.reiventar.server.core.ports.EncryptionEncoder;
import com.reiventar.server.core.ports.PassportEncoder;
import com.reiventar.server.core.repository.UserRepository;

public class AuthUsecase {
    private final UserRepository userRepository;
    private final EncryptionEncoder encryptionEncoder;
    private final PassportEncoder passportEncoder;

    public AuthUsecase(
        UserRepository userRepository,
        EncryptionEncoder encryptionEncoder,
        PassportEncoder passportEncoder
    ) {
        this.userRepository = userRepository;
        this.encryptionEncoder = encryptionEncoder;
        this.passportEncoder = passportEncoder;
    }

    public String authenticate(String name, String password) {
        try {
            User user = this.userRepository.getByName(name);

            if (user == null) {
                throw new AuthError.InvalidCredentials(false);
            }

            if (!this.encryptionEncoder.compare(user.password, password)) {
                throw new AuthError.InvalidCredentials(true);
            }

            return this.passportEncoder.encode(user.uuid, user.username, user.createdAt);
        } catch (AuthError error) {
            throw error;
        }
    }
}
