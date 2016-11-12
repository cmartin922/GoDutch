package com.hackfa16.godutch;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cmartin on 11/12/16.
 */

public class RContents implements Serializable {
    REntry total;
    REntry tax;

    ArrayList<REntry> items;


    public RContents(REntry to, REntry ta, ArrayList<REntry> i){
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
    public ArrayList<REntry> getItems(){
        return items;
    }
}
