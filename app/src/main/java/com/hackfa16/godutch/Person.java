package com.hackfa16.godutch;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KonstantinChen on 11/11/16.
 */

public class Person {
    private int color;
    private double unTippedTotal;
    private double tipRate;
    private double tip;
    private double taxRate;
    private double tax;
    private List<REntry> items;
    private List<String> names;
    private List<Double> prices;

    public Person(int c, double tr, double t){
        color = c;
        taxRate = tr;
        items = new ArrayList<>();
        tipRate = t;
        unTippedTotal = 0;
    }

    public void addREntry(REntry entry){
        items.add(entry);
    }

    private void splitList(){
        for(REntry i:items){
            names.add(i.getName());
            prices.add(i.getPrice());
        }
    }

    public int getColor() {return color; }

    public List<String> getNames(){
        return names;
    }

    public List<Double> getPrices(){
        return prices;
    }

    public List<REntry> getItems() {return items;}

    public void calculate(){
        for(REntry i:items){
            unTippedTotal+=i.getPrice();
        }
        tax = unTippedTotal*taxRate;
        tip = tipRate*(unTippedTotal+tax);
    }

    public double getTotal(){
        calculate();
        return unTippedTotal+tip+tax;
    }

    public double getTax(){
        return tax;
    }

    public double getTip() { return tip; }
}