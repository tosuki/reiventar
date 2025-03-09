package com.reiventar.server.core.ports;

import com.reiventar.server.core.model.Permissions;
import com.reiventar.server.core.model.User;

public interface UserRepository {
    User create(String name, String password, Permissions permissions);
    
    User get(long id);
    User get(String name);

    void delete(long id);
    void delete(String name);
}
