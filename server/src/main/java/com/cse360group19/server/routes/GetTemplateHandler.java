package com.cse360group19.server.routes;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpHandler;
import com.cse360group19.Utility;
import com.sun.net.httpserver.HttpExchange;

import org.json.simple.JSONObject;

public class GetTemplateHandler implements HttpHandler {

    static final int OK = 200;
    static final int INTERNAL_SERVER_ERROR = 500;

    public void handle(HttpExchange exchange) throws IOException {
        try {
            String rawQuery = exchange.getRequestURI().getRawQuery();
            Map<String, List<String>> inputObject = Utility.parseQuery(rawQuery);

            HashMap<String, Object> outputObject = new HashMap<String, Object>();
            outputObject.put("testOutput", inputObject.get("testInput").get(0));

            String response = new JSONObject(outputObject).toJSONString();
            exchange.sendResponseHeaders(OK, response.length());

            OutputStream stream = exchange.getResponseBody();
            stream.write(response.getBytes());
            stream.close();
        }
        catch(Exception e) {
            exchange.sendResponseHeaders(INTERNAL_SERVER_ERROR, 0);
            OutputStream stream = exchange.getResponseBody();
            stream.close();
        }
    }
}
