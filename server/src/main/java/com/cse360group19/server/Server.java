package com.cse360group19.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.cse360group19.server.handlers.GetTemplateHandler;
import com.cse360group19.server.handlers.PostTemplateHandler;
import com.cse360group19.server.handlers.UniversalHandler;

import com.sun.net.httpserver.HttpServer;

public class Server {
    private int port;
    
    public Server() {
        port = 3000;
    }

    public Server(int port) {
        this.port = port;
    }

    public void listen() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", new UniversalHandler());
        server.createContext("/api/gettemplate", new GetTemplateHandler());
        server.createContext("/api/posttemplate", new PostTemplateHandler());

        System.out.println("Server listening on port " + port);

        server.start();
    }
}
