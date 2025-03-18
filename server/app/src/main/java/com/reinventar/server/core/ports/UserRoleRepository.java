package com.reinventar.server.core.ports;

import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.model.UserRole;

public interface UserRoleRepository {
    UserRole create(long userId, Permissions permission);
    UserRole getById(long userId);
    UserRole update(long userId, Permissions permission);
}

