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

public class ModifyOrderStatusHandler implements HttpHandler {

    static final int OK = 200;
    static final int INTERNAL_SERVER_ERROR = 500;
    UpdateStatus UpdateStatus;

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
        try{

            int changeOrderStatus;  //user input in which pizza
            String newStatus; //user input
            List<Order> orders = orderStorage.orders;

            for(int i =0; i < orders.size(); i++){  //goes through the list of orders
                if(orders.get(i).id == changeOrderStatus){
                    if(newStatus.equals("READY to COOK")){
                        order.status = "READY to COOK";
                    }
                    else if(newStatus.equals("COOKING")){
                        order.status = "COOKING";
                    }
                    else if(newStatus.equals("READY")){
                        order.status = "READY";
                    }
                    else{
                        order.status = "ACCEPTED";
                    }
                }

            }

        }
		
	}
    
}
