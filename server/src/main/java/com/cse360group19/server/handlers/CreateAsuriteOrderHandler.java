package com.cse360group19.server.handlers;

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

public class CreateAsuriteOrderHandler implements HttpHandler {

    static final int OK = 200;
    static final int INTERNAL_SERVER_ERROR = 500;
    OrderStorage orderStorage;

    // string array to hold valid ASURITE ID numbers
    String[] ValidAsurite = {"111","222","333","444","555","666","777","888","999"};

    public CreateAsuriteOrderHandler(OrderStorage orderStorage) {
        this.orderStorage = orderStorage;
    }

    public void handle(HttpExchange exchange) throws IOException {
        try {
            InputStream body = exchange.getRequestBody();
            InputStreamReader bodyReader = new InputStreamReader(body);
            JSONObject inputObject = (JSONObject) JSONValue.parse(bodyReader);

            System.out.println(inputObject.toJSONString());

            // create json object to hold asurite ID
            JSONObject asuRite = (JSONObject) inputObject.get("asuRiteID");

            // variable to hold asurite id
            int asuRiteID;

            // extract asuRiteID from json object, then print to test
            asuRiteID = Integer.parseInt(asuRite.get("asuRiteID").toString());
            System.out.println(asuRiteID);
            
            // bool var to hold result if id was valid
            boolean isValid = false;

            // check if asuRiteId was valid
            for (int j = 0; j < ValidAsurite.length; j++){
                int asuID = Integer.parseInt(ValidAsurite[j]);

                // check if asuRite Id is valid for this iteration
                if (asuRiteID == asuID){
                    isValid = true;
                    break;
                }
            }

            // if asuRiteID is not valid throw an exception
            if (isValid == false){
                throw new Exception();
            }
            
            
            Order newOrder = new Order();

            JSONArray items = (JSONArray) inputObject.get("order");

            for (int i = 0; i < items.size(); i++) {
                JSONObject item = (JSONObject) items.get(i);

                System.out.println(item.get("quantity"));

                int quantity;
                String id;
                String[] options;

                quantity = Integer.parseInt(item.get("quantity").toString());
                System.out.println(quantity);

                id = item.get("id").toString();
                System.out.println(id);

                Object[] rawOptions = ((JSONArray) item.get("options")).toArray();
                options = new String[rawOptions.length];

                for (int j = 0; j < rawOptions.length; j++) {
                    options[j] = rawOptions[j].toString();
                }

                newOrder.items.add(new Item(options, id, quantity));
            }

            this.orderStorage.orders.add(newOrder);

            HashMap<String, Object> outputObject = new HashMap<String, Object>();
            outputObject.put("id", newOrder.id);

            String response = new JSONObject(outputObject).toJSONString();
            exchange.sendResponseHeaders(OK, response.length());

            OutputStream stream = exchange.getResponseBody();
            stream.write(response.getBytes());
            stream.close();
        } catch (Exception e) {
            exchange.sendResponseHeaders(INTERNAL_SERVER_ERROR, 0);
            OutputStream stream = exchange.getResponseBody();
            stream.close();
        }
    }
}
