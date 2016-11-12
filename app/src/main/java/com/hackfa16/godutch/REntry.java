package com.hackfa16.godutch;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by cmartin on 11/12/16.
 */

public class REntry implements Serializable {
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

    public String toString(){
        return getName()+" "+getPrice();
    }

}
