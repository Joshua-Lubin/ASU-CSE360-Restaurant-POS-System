package com.cse360group19;

import java.io.IOException;

import com.cse360group19.server.Server;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        Server server = new Server();

        try {
            server.listen();
        }
        catch(IOException exception) {
            System.out.println(exception);
        }
    }
}
