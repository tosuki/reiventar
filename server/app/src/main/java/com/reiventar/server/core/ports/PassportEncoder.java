package com.reiventar.server.core.ports;

import com.reiventar.server.core.model.Permissions;
import com.reiventar.server.core.model.Session;

public interface PassportEncoder {
    String encode(
        long id,
        String name,
        Permissions permission,
        long createdAt,
        long updatedAt
    );

    Session decode(String passport);
}
