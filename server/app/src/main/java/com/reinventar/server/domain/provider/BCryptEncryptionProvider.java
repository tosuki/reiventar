package com.reinventar.server.domain.provider;

import com.reinventar.server.core.ports.EncryptionProvider;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;

public class BCryptEncryptionProvider implements EncryptionProvider {
    public final int cost;
    public final Hasher hasher = BCrypt.withDefaults();

    public BCryptEncryptionProvider(int cost) {
        this.cost = cost;
    }

    @Override
    public String encrypt(String value) {
        return hasher.hashToString(cost, value.toCharArray());
    }

    @Override
    public boolean compare(String encrypted, String value) {
        BCrypt.Result result = BCrypt.verifyer().verify(encrypted.toCharArray(), value.toCharArray());

        return result.verified;
    }
}
