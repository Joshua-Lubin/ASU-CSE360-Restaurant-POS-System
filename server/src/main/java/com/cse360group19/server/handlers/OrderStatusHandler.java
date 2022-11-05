package com.cse360group19.server.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpHandler;
import com.cse360group19.server.Item;
import com.cse360group19.server.Order;
import com.cse360group19.server.OrderStorage;
import com.cse360group19.server.Utility;
import com.sun.net.httpserver.HttpExchange;

import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class OrderStatusHandler implements HttpHandler {

    static final int OK = 200;
    static final int INTERNAL_SERVER_ERROR = 500;
    OrderStorage orderStorage;

    public OrderStatusHandler(OrderStorage orderStorage) {
        this.orderStorage = orderStorage;
    }
    
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String rawQuery = exchange.getRequestURI().getRawQuery();
            Map<String, List<String>> inputObject = Utility.parseQuery(rawQuery);

            int id = Integer.parseInt(inputObject.get("id").get(0));

            Order order = new Order();
            List<Order> orders = orderStorage.orders;

            for(int i = 0; i < orders.size(); i++) {
                if(orders.get(i).id == id) {
                    order = orders.get(i);
                    break;
                }
            }

            JSONArray items = new JSONArray();

            for(int i = 0; i < order.items.size(); i++) {
                Item currentItem = order.items.get(i);

                JSONObject item = new JSONObject();

                item.put("id", currentItem.id);
                item.put("quantity", currentItem.quantity);

                JSONArray options = new JSONArray();

                for(int j = 0; j < currentItem.options.length; j++) {
                    options.add(currentItem.options[j]);
                }

                item.put("options", options);

                items.add(item);
            }

            HashMap<String, Object> outputObject = new HashMap<String, Object>();
            outputObject.put("id", order.id);
            outputObject.put("status", order.status);
            outputObject.put("items", items);
            
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
