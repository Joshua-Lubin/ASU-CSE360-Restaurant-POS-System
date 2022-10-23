package com.cse360group19.server.handlers;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class ApiHandler implements HttpHandler {

    static final int OK = 200;

    public void handle(HttpExchange exchange) throws IOException {
        String response = "<h1>API working " + exchange.getRequestURI() + "</h1>";

        exchange.sendResponseHeaders(OK, response.length());

        OutputStream stream = exchange.getResponseBody();
        stream.write(response.getBytes());
        stream.close();
    }
}
