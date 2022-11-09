package com.cse360group19.server;

import java.util.LinkedList;
import java.util.List;

public class OrderStorage {

    public List<Order> orders;

    public OrderStorage() {
        this.orders = new LinkedList<Order>();
        /*
        String[] str = new String[0];
        Item temp = new Item(str, "cheese", 1);
        Order order = new Order();
        order.items.add(temp);
        orders.add(order);
        */
    }
}
