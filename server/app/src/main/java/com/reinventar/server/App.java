package com.reinventar.server;

import com.reinventar.server.core.Logger;
import com.reinventar.server.core.errors.CoreError;
import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.model.Session;
import com.reinventar.server.core.model.User;
import com.reinventar.server.core.ports.PassportEncoder;
import com.reinventar.server.domain.provider.PassportEncoderImpl;
import com.reinventar.server.domain.provider.PostgresDatabaseProvider;
import com.reinventar.server.domain.repository.UserPostgresRepositoryImpl;

public class App {
    public static void main(String[] args) {
        try {
            PassportEncoder passportEncoder = new PassportEncoderImpl("aadawdad".getBytes());

            String passport = passportEncoder.encode(2, "aa",Permissions.ADMINISTRATOR, 0, 0);

            Logger.warn(passport);

            Session decoded = passportEncoder.decode(passport);
        } catch (CoreError error) {
            Logger.error(error);
        }
    }
}
