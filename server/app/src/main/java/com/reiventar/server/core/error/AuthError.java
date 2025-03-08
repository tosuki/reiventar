package com.reiventar.server.core.error;

public abstract class AuthError extends ReiventarError {
    public AuthError(String message) {
        super("Auth", message);
    }

    abstract boolean isError(Object error);

    public static class InvalidCredentials extends AuthError {
        private static String getExceptionMessage(boolean isPasswordInvalid) {
            if (isPasswordInvalid) {
                return "The password is invalid";
            }

            return "The username is invalid";
        }

        public InvalidCredentials(boolean isPasswordInvalid) {
            super(getExceptionMessage(isPasswordInvalid));
        }

        @Override
        boolean isError(Object error) {
            return error instanceof InvalidCredentials;
        }
    }
}