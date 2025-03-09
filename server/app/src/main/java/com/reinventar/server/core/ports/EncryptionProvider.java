package com.reinventar.server.core.ports;

public interface EncryptionProvider {
    String encrypt(String value);
    boolean compare(String encrypted, String value);
}
