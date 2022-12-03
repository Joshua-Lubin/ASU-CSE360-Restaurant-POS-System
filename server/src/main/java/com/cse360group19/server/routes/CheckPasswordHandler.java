package com.cse360group19.server.routes;

import com.cse360group19.data_structures.PasswordStorage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class CheckPasswordHandler implements HttpHandler {
    static final int OK = 200;
    static final int INTERNAL_SERVER_ERROR = 500;

    PasswordStorage passwordStorage;

    public CheckPasswordHandler(PasswordStorage passwordStorage) {
        this.passwordStorage = passwordStorage;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            InputStream body = exchange.getRequestBody();
            InputStreamReader bodyReader = new InputStreamReader(body);
            JSONObject inputObject = (JSONObject)JSONValue.parse(bodyReader);

            String password = (String) inputObject.get("password");
            
            if(!passwordStorage.checkPassword(password)) {
                throw(new Exception());
            }

            exchange.sendResponseHeaders(OK, 0);
            OutputStream stream = exchange.getResponseBody();
            stream.close();

        } catch (Exception e) {
            System.out.println(e);
            exchange.sendResponseHeaders(INTERNAL_SERVER_ERROR, 0);
            OutputStream stream = exchange.getResponseBody();
            stream.close();
        }

    }
}
