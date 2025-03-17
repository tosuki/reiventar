package com.reinventar.server.domain.factory;

import com.reinventar.server.core.ports.EncryptionProvider;
import com.reinventar.server.core.ports.PassportEncoder;
import com.reinventar.server.domain.provider.BCryptEncryptionProvider;
import com.reinventar.server.domain.provider.PassportEncoderImpl;

public class PortsFactory {
    public static EncryptionProvider newEncryptionProvider() {
        return new BCryptEncryptionProvider();
    }

    public static PassportEncoder newPassportEncoder(String secret) {
        return new PassportEncoderImpl(secret.getBytes());
    }
}
