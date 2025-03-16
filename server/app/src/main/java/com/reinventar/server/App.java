package com.reinventar.server;

import com.reinventar.server.core.Logger;
import com.reinventar.server.core.errors.CoreError;
import com.reinventar.server.core.ports.EncryptionProvider;
import com.reinventar.server.domain.provider.BCryptEncryptionProvider;

public class App {
    public static void main(String[] args) {
        try {
            EncryptionProvider encryptionProvider = new BCryptEncryptionProvider();
            String encrypted = encryptionProvider.encrypt("hello");

            System.out.println(encrypted);
            System.out.println(encryptionProvider.compare(encrypted, "hello"));
        } catch (CoreError error) {
            Logger.error(error);
        }
    }
}
