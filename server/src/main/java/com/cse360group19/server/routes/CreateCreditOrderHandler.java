package com.cse360group19.server.routes;

// import necessary libraries
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;

import com.sun.net.httpserver.HttpHandler;
import com.cse360group19.data_structures.CreditCard;
import com.cse360group19.data_structures.Item;
import com.cse360group19.data_structures.Order;
import com.cse360group19.data_structures.OrderStorage;
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

            // create new order object
            Order newOrder = new Order();

            // create json object Array to hold order items
            JSONArray items = (JSONArray) inputObject.get("order");

            // create json object to hold credit card
            JSONObject credCard = (JSONObject) inputObject.get("creditCard");

            // variables to hold credit card details
            long cardNumber;
            int cvv;
            String expDate;

            // extract cardNumber from creditCard object, then use print to test if it works properly
            cardNumber = Long.parseLong(credCard.get("cardNumber").toString());

            // extract cvv from creditCard object, then use the print to tests if it works properly
            cvv = Integer.parseInt(credCard.get("cvv").toString());

            // extract expiration date from credit card object, then print to test
            expDate = credCard.get("expDate").toString();

            // create credit card object using the parameters we extracted
            CreditCard creditCard = new CreditCard(cardNumber, cvv, expDate);

            // check if carNumber and cvv are valid
            if (creditCard.isCardNumValid(cardNumber) == false || creditCard.isCvvValid(cvv) == false){
                // if card details were not valid then throw an exception
                throw new Exception();
            }

            // variables to hold order details
            int quantity;       // # of pizzas order
            String id;          // cheese | pepperoni | vegetable
            String[] options;   // olives | extraCheese | mushrooms | onions

            // use for loop to extract all item details
            for (int i = 0; i < items.size(); i++){
                // create a JSON object for order then extract details
                JSONObject item = (JSONObject) items.get(i);

                // extract quantity from item
                quantity = Integer.parseInt(item.get("quantity").toString());

                // extract id from item
                id = item.get("id").toString();

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
