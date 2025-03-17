package com.reinventar.server.core.usecase;

import com.reinventar.server.core.ports.CustomerRepository;
import com.reinventar.server.core.ports.UserRepository;

public class CustomerUsecase {
    public final CustomerRepository customerRepository;
    public final UserRepository userRepository;

    public CustomerUsecase(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }
}

