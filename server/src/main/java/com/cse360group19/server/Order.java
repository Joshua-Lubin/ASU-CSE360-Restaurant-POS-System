package com.cse360group19.server;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Order {
    public int id;
    public List<Item> items;
    public String status;

    public Order() {
        this.items = new LinkedList<Item>();
        this.status = "accepted";
        Random random = new Random();
        this.id = random.nextInt(999999);
    }
}
