package com.hackfa16.godutch;

/**
 * Created by cmartin on 11/12/16.
 */

public class REntry {
    String name;
    String price;
    public REntry(String n, String p) {
        name = n;
        price = p;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return Double.parseDouble(price);
    }
}
