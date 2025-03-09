package com.reinventar.server.core.errors;

public abstract class AuthError extends CoreError {
    public AuthError(String message) {
        super("Auth", message);
    }

    public static class InvalidCredentials extends AuthError {
        public InvalidCredentials() {
            super("Invalid credentials");
        }
    }

    public static class InvalidPassport extends AuthError {
        public InvalidPassport() {
            super("Invalid passport");
        }
    }

    public static class ExpiredPassport extends AuthError {
        public ExpiredPassport() {
            super("Expired passport");
        }
    }

    public static class NameOccupied extends AuthError {
        public NameOccupied(String name) {
            super("The name " + name + " is occupied");
        }
    }
}
