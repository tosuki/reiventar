package com.reinventar.server.core.ports;

import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.model.Session;

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
