package com.cse360group19.server;

import java.util.LinkedList;
import java.util.List;

public class OrderStorage {

    public List<Order> orders;

    public OrderStorage() {
        this.orders = new LinkedList<Order>();
    }
}
