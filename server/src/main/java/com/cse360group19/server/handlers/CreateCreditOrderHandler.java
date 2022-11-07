package com.cse360group19.server.handlers;

// import necessary libraries
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;

import com.sun.net.httpserver.HttpHandler;
import com.cse360group19.server.Item;
import com.cse360group19.server.Order;
import com.cse360group19.server.OrderStorage;
import com.sun.net.httpserver.HttpExchange;

import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class CreateCreditOrderHandler implements HttpHandler{

    // response integers for Http Protocol
    static final int OK = 200;
    static final int INTERNAL_SERVER_ERROR = 500;

    // declare order storage object
    OrderStorage orderStorage;

    // constructor
    public CreateCreditOrderHandler(OrderStorage orderStorage){
        this.orderStorage = orderStorage;
    }
    
    // Override the handle method from HttpHandler interface
    public void handle(HttpExchange exchange) throws IOException {
        try{
            // get input
            InputStream body = exchange.getRequestBody();
            InputStreamReader bodyReader = new InputStreamReader(body);
            JSONObject inputObject = (JSONObject)JSONValue.parse(bodyReader);

            System.out.println(inputObject.toJSONString());

            // create new order object
            Order newOrder = new Order();

            // create json object to hold order items
            JSONArray items = (JSONArray) inputObject.get("order");

            // variables to hold order details
            int quantity;       // # of pizzas order
            String id;          // cheese | pepperoni | vegetable
            String[] options;   // olives | extraCheese | mushrooms | onions

            // use for loop to extract all item details
            for (int i = 0; i < items.size(); i++){
                // create a JSON object for order then extract details
                JSONObject item = (JSONObject) items.get(i);

                System.out.println(item.get("quantity"));

                // extract quantity from item
                quantity = Integer.parseInt(item.get("quantity").toString());
                System.out.println(quantity);

                // extract id from item
                id = item.get("id").toString();
                System.out.println(id);

                // extract options from item
                Object[] rawOptions = ((JSONArray) item.get("options")).toArray();
                options = new String[rawOptions.length];

                // initialize options string array
                for (int j = 0; j < rawOptions.length; j++){
                    options[j] = rawOptions[j].toString();
                }

                // add the items to the new order
                newOrder.items.add(new Item(options, id, quantity));
            }
            // add newOrder to our orderStorage
            this.orderStorage.orders.add(newOrder);

            // create output object
            HashMap<String, Object> outputObject = new HashMap<String, Object>();
            outputObject.put("id", newOrder.id);

            // create a string for our http response
            String response = new JSONObject(outputObject).toJSONString();
            exchange.sendResponseHeaders(OK, response.length());

            // write the response to the user
            OutputStream stream = exchange.getResponseBody();
            stream.write(response.getBytes());
            stream.close();
        }
        catch(Exception e){
            exchange.sendResponseHeaders(INTERNAL_SERVER_ERROR, 0);
            OutputStream stream = exchange.getResponseBody();
            stream.close();
        }
    }
}