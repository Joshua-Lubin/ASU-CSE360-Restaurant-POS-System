package com.cse360group19.server.routes;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.net.httpserver.HttpHandler;
import com.cse360group19.data_structures.Item;
import com.cse360group19.data_structures.Order;
import com.cse360group19.data_structures.OrderStorage;
import com.sun.net.httpserver.HttpExchange;

import org.json.simple.JSONObject;

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

            List<Object> outputArray = new ArrayList<Object>();

            for(int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);

                List<JSONObject> items = new ArrayList<JSONObject>();

                for(int k = 0; k < order.items.size(); k++) {
                    Item currentItem = order.items.get(k);

                    HashMap<String, Object> item = new HashMap<String, Object>();

                    item.put("id", currentItem.id);
                    item.put("quantity", currentItem.quantity);

                    List<String> options = new ArrayList<String>();

                    for(int j = 0; j < currentItem.options.length; j++) {
                        options.add(currentItem.options[j]);
                    }

                    item.put("options", options);

                    items.add(new JSONObject(item));
                }

                HashMap<String, Object> outputOrder = new HashMap<String, Object>();
                outputOrder.put("id", order.id);
                outputOrder.put("status", order.status);
                outputOrder.put("items", items);

                outputArray.add(outputOrder);
            }

            HashMap<String, Object> outputObject = new HashMap<String, Object>();
            outputObject.put("orders", outputArray);

            JSONObject jsonOutput = new JSONObject(outputObject);
            
            String response = jsonOutput.toJSONString();
            exchange.sendResponseHeaders(OK, response.length());

            OutputStream stream = exchange.getResponseBody();
            stream.write(response.getBytes());
            stream.close();
        }
        catch(Exception e) {
            System.out.println(e);
            exchange.sendResponseHeaders(INTERNAL_SERVER_ERROR, 0);
            OutputStream stream = exchange.getResponseBody();
            stream.close();
        }
    }
}
