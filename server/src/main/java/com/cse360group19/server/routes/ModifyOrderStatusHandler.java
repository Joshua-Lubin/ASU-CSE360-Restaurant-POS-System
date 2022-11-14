package com.cse360group19.server.routes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.net.httpserver.HttpHandler;
import com.cse360group19.data_structures.Order;
import com.cse360group19.data_structures.OrderStorage;
import com.sun.net.httpserver.HttpExchange;

public class ModifyOrderStatusHandler implements HttpHandler {

    static final int OK = 200;
    static final int INTERNAL_SERVER_ERROR = 500;
    OrderStorage orderStorage;

    public ModifyOrderStatusHandler(OrderStorage orderStorage) {
        this.orderStorage = orderStorage;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            InputStream body = exchange.getRequestBody();
            InputStreamReader bodyReader = new InputStreamReader(body);
            JSONObject inputObject = (JSONObject)JSONValue.parse(bodyReader);

            int changeOrderStatus = (int) (long) inputObject.get("id"); // user input in which pizza to change status
            String newStatus = (String) inputObject.get("status"); // user input to new status
            List<Order> orders = orderStorage.orders;

            for (int i = 0; i < orders.size(); i++) { // goes through the list of orders
                if (orders.get(i).id == changeOrderStatus) {
                    orders.get(i).status = newStatus;
                }
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
