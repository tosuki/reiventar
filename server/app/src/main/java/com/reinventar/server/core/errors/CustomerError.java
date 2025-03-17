package com.reinventar.server.core.errors;

public abstract class CustomerError extends CoreError {
    public CustomerError(String message) {
        super("Customer", message);
    }

    public static class OccupiedName extends CustomerError {
        public OccupiedName(String name) {
            super("There is already a customer using the name " + name);
        }
    } 
}
