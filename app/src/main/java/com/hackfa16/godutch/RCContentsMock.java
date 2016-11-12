package com.hackfa16.godutch;

/**
 * Created by Macbook on 11/12/16.
 */

public class RCContentsMock {
    public RContents rContents;

    public RCContentsMock() {
        REntry entry1 = new REntry("Item1", "1");
        REntry entry2 = new REntry("Item2", "2");
        REntry entry3 = new REntry("Item3", "3");
        REntry entry4 = new REntry("Item4", "4");
        REntry entry5 = new REntry("Item5", "5");
        REntry entry6 = new REntry("Item6", "6");
        REntry entry7 = new REntry("Item7", "7");
        REntry total = new REntry("Total", "28");
        REntry tax = new REntry("Tax", "5");
        REntry[] items = {entry1, entry2, entry3, entry4, entry5, entry6, entry7};
        rContents = new RContents();
        rContents.recContents(total, tax, items);
    }
}
