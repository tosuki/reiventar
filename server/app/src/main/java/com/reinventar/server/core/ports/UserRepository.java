package com.reinventar.server.core.ports;

import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.model.User;

public interface UserRepository {
    void initialize();
    User create(String name, String password, Permissions permissions);
    
    User get(long id);
    User get(String name);

    void delete(long id);
    void delete(String name);
}
