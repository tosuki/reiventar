package com.reinventar.server.domain.provider;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.reinventar.server.core.Logger;
import com.reinventar.server.core.errors.AuthError;
import com.reinventar.server.core.errors.CriticalError;
import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.model.Session;
import com.reinventar.server.core.ports.PassportEncoder;

public class PassportEncoderImpl implements PassportEncoder {
    private final byte[] secret;

    public PassportEncoderImpl(byte[] secret) {
        this.secret = secret;
    }


    @Override
    public String encode(long id, String name, Permissions permission, long createdAt, long updatedAt) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        
        try {
            long expires_at, issued_at;

            issued_at = new Date().getTime();
            expires_at = issued_at + 60*60*72;
            String session = JSONEncoder.encodeSession(new Session(id, name, permission, createdAt, updatedAt, expires_at, issued_at));

            String passport = JWT.create()
                .withClaim("session", session)
                .sign(algorithm);

            return passport;
        } catch (JWTCreationException exception) {
            throw new CriticalError.JWTError(exception);
        }
    }

    @Override
    public Session decode(String passport) {
        try {
            DecodedJWT decodedJWT = JWT.decode(passport);

            Claim claim = decodedJWT.getClaim("session");
            
            if (claim == null) {
                throw new AuthError.InvalidPassport();
            }

            Session session = JSONEncoder.decodeJson(claim.asString());

            return session;
        } catch (JWTDecodeException exception) {
            throw new CriticalError.JWTError(exception);
        }
    }
    
}
