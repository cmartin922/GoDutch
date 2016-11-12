package com.hackfa16.godutch;

/**
 * Created by KonstantinChen on 11/11/16.
 */

class Item {
    String item;
    double price;

    Item(String i, double p){
        item = i;
        price = p;
    }

    String getName(){
        return item;
    }

    double getPrice(){
        return price;
    }
}
