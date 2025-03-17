package com.reinventar.server.core.ports;

import com.reinventar.server.core.model.Customer;

public interface CustomerRepository {
    void initialize();

    Customer create(String name);
    Customer get(String name);
    Customer get(long id);
    
    void delete(String name);
    void delete(long id);
}
