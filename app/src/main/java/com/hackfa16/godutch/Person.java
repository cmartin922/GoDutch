package com.hackfa16.godutch;

import android.view.View;

/**
 * Created by KonstantinChen on 11/11/16.
 */

public class Person {
    private char color;
    private double unTippedTotal;
    private double tip;
    private double total;
    private double taxRate;
    private double tax;
    private Item[] items;

    public Person(char c, View v, double tr, Item[] its, double t){
        color = c;
        taxRate = tr;
        items = its;
        tip = t;
        unTippedTotal = 0;
        calculateTotalBeforeTip();

    }

    public void calculateTotalBeforeTip(){
        for(Item i:items){
            unTippedTotal+=i.getPrice();
        }
        tax = unTippedTotal*taxRate;
        unTippedTotal+=tax;
    }
}
