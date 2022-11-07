package com.cse360group19.server;

public class Item {
    private String[] options;
    public String id;
    public int quantity;
    
    public Item(String[] options, String id, int quantity) {
        this.options = options;
        this.id = id;
        this.quantity = quantity;
    }

    public String[] getOptions() {
        if(options.length == 0) {
            String[] res = {"None"};
            return res;
        }
        return options;
    }
}
