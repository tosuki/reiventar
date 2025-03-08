package com.reiventar.server.core.repository;

import com.reiventar.server.core.model.User;

public interface UserRepository {
    User add(String uuid, String username, String password);
    User getByUUID(String uuid);
}
