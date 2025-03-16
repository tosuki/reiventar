package com.reinventar.server.core.errors;

import java.sql.SQLException;
import org.json.simple.parser.ParseException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;

public abstract class CriticalError extends CoreError {
    public CriticalError(String message, String layer) {
        super("Critical - " + layer, message);
    }

    public CriticalError(String message) {
        super("Critical", message);
    }

    public static class EncryptionError extends CriticalError {
        public EncryptionError(IllegalArgumentException exception) {
            super(exception.getMessage(), "password encryption");
        }
    }

    public static class JSONParseError extends CriticalError {
        public JSONParseError(ClassCastException exception) {
            super(exception.getMessage(), "passport session encoding");
        }

        public JSONParseError(ParseException exception) {
            super(exception.getMessage(), "passport session encoding");
        }
    }

    public static class UnhandledError extends CriticalError {
        public UnhandledError(String message) {
            super(message);
        }

        public UnhandledError(String message, String layer) {
            super(message, layer);
        }
    }

    public static class DatabaseSQLError extends CriticalError {
        public DatabaseSQLError(SQLException exception) {
            super(exception.getMessage());
        }
        
        public DatabaseSQLError(SQLException exception, String layer) {
            super(exception.getMessage(), layer);
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
