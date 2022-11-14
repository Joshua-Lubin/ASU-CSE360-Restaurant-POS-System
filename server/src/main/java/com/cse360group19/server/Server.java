package com.cse360group19.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.cse360group19.data_structures.OrderStorage;
import com.cse360group19.data_structures.PasswordStorage;
import com.cse360group19.server.routes.AllOrderStatusHandler;
import com.cse360group19.server.routes.CreateAsuriteOrderHandler;
import com.cse360group19.server.routes.GetTemplateHandler;
import com.cse360group19.server.routes.ModifyOrderStatusHandler;
import com.cse360group19.server.routes.OrderStatusHandler;
import com.cse360group19.server.routes.PostTemplateHandler;
import com.cse360group19.server.routes.UniversalHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {
    private HttpServer server;

    public void createServer(int port, OrderStorage orderStorage, PasswordStorage passwordStorage) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", new UniversalHandler());
        server.createContext("/api/gettemplate", new GetTemplateHandler());
        server.createContext("/api/posttemplate", new PostTemplateHandler());
        server.createContext("/api/create-asurite-order", new CreateAsuriteOrderHandler(orderStorage));
        server.createContext("/api/order-status", new OrderStatusHandler(orderStorage));
        server.createContext("/api/all-order-status", new AllOrderStatusHandler(orderStorage));
        server.createContext("/api/modify-order-status", new ModifyOrderStatusHandler(orderStorage));
    }

    public void listen() {
        server.start();
    }

    public void stop() {
        server.stop(0);
    }

    public int getPort() {
        return server.getAddress().getPort();
    }
}
