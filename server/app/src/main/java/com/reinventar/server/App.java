package com.reinventar.server;

import com.reinventar.server.core.Logger;
import com.reinventar.server.core.errors.CoreError;
import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.model.Session;
import com.reinventar.server.core.ports.PassportEncoder;
import com.reinventar.server.domain.provider.PassportEncoderImpl;

public class App {
    public static void main(String[] args) {
        try {
            PassportEncoder passportEncoder = new PassportEncoderImpl("hello world".getBytes());

            String passport = passportEncoder.encode(2, "hello", Permissions.MANAGER, 0, 0);
            Session session = passportEncoder.decode(passport);

            System.out.println(session.permission);
        } catch (CoreError error) {
            Logger.error(error);
        }
    }
}
