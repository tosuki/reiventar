package com.reiventar.server.core.ports;

import com.reiventar.server.core.model.Session;

public interface PassportEncoder {
    String encode(
        String uuid,
        String username,
        long createdAt
    );

    Session decode(String passport);
}