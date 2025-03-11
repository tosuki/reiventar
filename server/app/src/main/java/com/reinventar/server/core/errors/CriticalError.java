package com.reinventar.server.core.errors;

import java.sql.SQLException;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;

public abstract class CriticalError extends CoreError {
    public CriticalError(String message) {
        super("Critical", message);
    }

    public static class UnhandledError extends CriticalError {
        public UnhandledError(String message) {
            super(message);
        }
    }

    public static class DatabaseSQLError extends CriticalError {
        public DatabaseSQLError(SQLException exception) {
            super(exception.getMessage());
        }
    }

    public static class JWTError extends CriticalError {
        public JWTError(JWTDecodeException exception) {
            super(exception.getMessage());
        }

        public JWTError(JWTCreationException exception) {
            super(exception.getMessage());
        }
    }

    public static class MissingDataSource extends CriticalError {
        public MissingDataSource() {
            super("Missing database data source");
        }
    }
}
