package com.reinventar.server.core.usecase;


import com.reinventar.server.core.errors.CoreError;
import com.reinventar.server.core.errors.CustomerError;
import com.reinventar.server.core.model.Customer;
import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.model.Session;
import com.reinventar.server.core.ports.CustomerRepository;
import com.reinventar.server.core.ports.UserRepository;

public class CustomerUsecase {
    public final CustomerRepository customerRepository;
    public final UserRepository userRepository;

    public CustomerUsecase(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public Customer createCustomer(Session session, String name) {
        try {
            if (session.permission != Permissions.MANAGER) {
                throw new CustomerError.NotEnoughtPermission(Permissions.MANAGER);
            }
 
            Customer customer = this.createCustomer(session, name);

            return customer;
        } catch (CoreError error) {
            throw error;
        }
    }
}

