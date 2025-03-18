package com.reinventar.server.core.errors;

import com.reinventar.server.core.model.Permissions;

public abstract class CustomerError extends CoreError {
    public CustomerError(String message) {
        super("Customer", message);
    }

    public static class NotEnoughtPermission extends CustomerError {
        public NotEnoughtPermission(Permissions required) {
            super("Attempt to acess restricted permission, required permission " + required.toString());
        }
    }

    public static class OccupiedName extends CustomerError {
        public OccupiedName(String name) {
            super("There is already a customer using the name " + name);
        }
    } 
}
