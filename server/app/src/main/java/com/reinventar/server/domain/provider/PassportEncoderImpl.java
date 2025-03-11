package com.reinventar.server.domain.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.reinventar.server.core.Logger;
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
            String passport = JWT.create()
                .withIssuer("aa")
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

            String issuer = decodedJWT.getIssuer();


            Logger.info(issuer);
            return null;
        } catch (JWTDecodeException exception) {
            throw new CriticalError.JWTError(exception);
        }
    }
    
}
