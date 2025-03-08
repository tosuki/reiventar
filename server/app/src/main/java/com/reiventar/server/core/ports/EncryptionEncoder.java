package com.reiventar.server.core.ports;

public interface EncryptionEncoder {
    String encrypt(String value);
    boolean compare(String encrypted, String value);
}
