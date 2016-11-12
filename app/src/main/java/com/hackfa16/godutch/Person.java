package com.hackfa16.godutch;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KonstantinChen on 11/11/16.
 */

public class Person {
    private char color;
    private double unTippedTotal;
    private double tip;
    private double taxRate;
    private double tax;
    private List<REntry> items;

    public Person(char c, View v, double tr, REntry[] its, double t){
        color = c;
        taxRate = tr;
        items = new ArrayList<>();
        tip = t;
        unTippedTotal = 0;
    }

    public void addREntry(REntry entry){
        items.add(entry);
    }

    public void calculateTotalBeforeTip(){
        for(REntry i:items){
            unTippedTotal+=i.getPrice();
        }
        tax = unTippedTotal*taxRate;
    }

    public double getTotal(){
        calculateTotalBeforeTip();
        return unTippedTotal+tip+tax;
    }

    public double getTax(){
        return tax;
    }
}
