package com.cse360group19.server;

public class Item {
    public String[] options;
    public String id;
    public int quantity;

    public Item(String[] options, String id, int quantity) {
        this.options = options;
        this.id = id;
        this.quantity = quantity;
    }
}
