package com.reinventar.server;

import com.reinventar.server.core.Logger;

public class App {
    public static void main(String[] args) {
        Logger.warn("This is a warn");
        Logger.error("This is an error");
        Logger.info("Tis is a info");
    }
}
