package com.reinventar.server.domain.provider;

import org.mindrot.jbcrypt.BCrypt;

import com.reinventar.server.core.errors.CriticalError;
import com.reinventar.server.core.ports.EncryptionProvider;

public class BCryptEncryptionProvider implements EncryptionProvider {
    @Override
    public String encrypt(String value) {
        try {
            return BCrypt.hashpw(value, BCrypt.gensalt()); 
        } catch (IllegalArgumentException exception) {
            throw new CriticalError.EncryptionError(exception);
        }
    }

    @Override
    public boolean compare(String encrypted, String value) {
        return BCrypt.checkpw(value, encrypted);
    }
    
}