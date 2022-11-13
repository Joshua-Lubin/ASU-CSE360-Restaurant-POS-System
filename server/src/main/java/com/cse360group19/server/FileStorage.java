package com.cse360group19.server;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

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



public class FileStorage {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader(orderStorage);
            JSONParser jsonParser = new JSONParser();
            return jsonParser.parse(reader);
           
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
      }
    

      public void storeOrderStorage(OrderStorage orderStorage) {


      }


}





