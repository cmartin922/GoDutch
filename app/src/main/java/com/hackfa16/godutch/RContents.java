package com.hackfa16.godutch;

/**
 * Created by cmartin on 11/12/16.
 */

public class RContents {
    REntry total;
    REntry tax;

    REntry[] items;

    public void recContents(REntry to, REntry ta, REntry[] i){
        total = to;
        tax = ta;
        items = i;

    }

    public REntry getTotal(){
        return total;
    }
    public REntry getTax(){
        return tax;
    }
    public REntry[] getItems(){
        return items;
    }
}
