package com.cse360group19.server.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.sun.net.httpserver.HttpHandler;
import com.cse360group19.server.Item;
import com.cse360group19.server.Order;
import com.cse360group19.server.OrderStorage;
import com.sun.net.httpserver.HttpExchange;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class AllOrderStatusHandler implements HttpHandler {

    static final int OK = 200;
    static final int INTERNAL_SERVER_ERROR = 500;
    OrderStorage orderStorage;

    public AllOrderStatusHandler(OrderStorage orderStorage) {
        this.orderStorage = orderStorage;
    }
    
    public void handle(HttpExchange exchange) throws IOException {
        try {
            List<Order> orders = orderStorage.orders;

            JSONArray outputArray = new JSONArray();

            for(int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);

                JSONArray items = new JSONArray();

                for(int k = 0; k < order.items.size(); k++) {
                    Item currentItem = order.items.get(i);

                    JSONObject item = new JSONObject();

                    item.put("id", currentItem.id);
                    item.put("quantity", currentItem.quantity);

                    JSONArray options = new JSONArray();

                    String[] currOptions = currentItem.getOptions();
                    for(int j = 0; j < currOptions.length; j++) {
                        options.add(currOptions[j]);
                    }

                    item.put("options", options);

                    items.add(item);
                }

                JSONObject outputOrder = new JSONObject();
                outputOrder.put("id", order.id);
                outputOrder.put("status", order.status);
                outputOrder.put("items", items);

                outputArray.add(outputOrder);
            }
            
            String response = outputArray.toJSONString();
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
