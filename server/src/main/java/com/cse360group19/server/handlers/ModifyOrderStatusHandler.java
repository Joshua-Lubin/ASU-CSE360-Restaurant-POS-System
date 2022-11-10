package com.cse360group19.server.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import com.sun.net.httpserver.HttpHandler;
import com.cse360group19.server.Order;
import com.cse360group19.server.OrderStorage;
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

            int changeOrderStatus = 0; // user input in which pizza to change status
            String newStatus = "accepted"; // user input to new status
            List<Order> orders = orderStorage.orders;

            for (int i = 0; i < orders.size(); i++) { // goes through the list of orders
                if (orders.get(i).id == changeOrderStatus) {
                    orders.get(i).status = newStatus;
                }

            }

        } catch (Exception e) {
            exchange.sendResponseHeaders(INTERNAL_SERVER_ERROR, 0);
            OutputStream stream = exchange.getResponseBody();
            stream.close();
        }

    }

}
