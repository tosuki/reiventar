package com.reinventar.server;

import com.reinventar.server.core.Logger;
import com.reinventar.server.core.errors.CoreError;
import com.reinventar.server.core.model.Permissions;
import com.reinventar.server.core.model.Session;
import com.reinventar.server.domain.provider.JSONEncoder;
import com.reinventar.server.domain.provider.PassportEncoderImpl;

public class App {
    public static void main(String[] args) {
        try {
            PassportEncoderImpl passportEncoder = new PassportEncoderImpl("awkd".getBytes());

            String passport = passportEncoder.encode(2, "hello", Permissions.ADMINISTRATOR, 0, 0);
            System.out.println(passport);

            Session decoded = passportEncoder.decode(passport);
            System.out.printf("The name is %s\n", decoded.name);
        } catch (CoreError error) {
            Logger.error(error);
        }
    }
}
