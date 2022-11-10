package com.cse360group19.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.cse360group19.server.handlers.AllOrderStatusHandler;
import com.cse360group19.server.handlers.CreateAsuriteOrderHandler;
import com.cse360group19.server.handlers.GetTemplateHandler;
import com.cse360group19.server.handlers.OrderStatusHandler;
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

    public void listen(OrderStorage orderStorage) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", new UniversalHandler());
        server.createContext("/api/gettemplate", new GetTemplateHandler());
        server.createContext("/api/posttemplate", new PostTemplateHandler());
        server.createContext("/api/create-asurite-order", new CreateAsuriteOrderHandler(orderStorage));
        server.createContext("/api/order-status", new OrderStatusHandler(orderStorage));
        server.createContext("/api/all-order-status", new AllOrderStatusHandler(orderStorage));

        System.out.println("Server listening on port " + port);
        
        server.start();
    }
}
