package com.hackfa16.godutch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Selector extends Activity {

    public RContents items;
    public Person user;
    public List<CheckBox> boxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView sv = new ScrollView(this);
        final LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);
        boxes = new ArrayList<CheckBox>();
        ArrayList<REntry> list = items.items;
        for (int i = 0; i < list.size(); i++){
            CheckBox check = new CheckBox(getApplicationContext());
            check.setText(list.get(i).getName()+"   "+list.get(i).getPrice());
            ll.addView(check);
        }
        Intent getRContents = getIntent();
    }

    public void checkBoxClicked (View view) {

    }
}
