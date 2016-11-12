package com.hackfa16.godutch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Selector extends Activity {

    public RContents items;
    public List<Person> users;
    public List<CheckBox> boxes;
    public List<Integer> colors;
    public int number = 0;
    public double tr;
    public double tipRate;
    TextView colorDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView sv = new ScrollView(this);

        // Colors
        colors = new ArrayList<Integer>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.GRAY);

        // Tax Rate
        tr = (items.getTax().getPrice())/(items.getTotal().getPrice());

        // Tip
        Intent intent = getIntent();
        tipRate = intent.getDoubleExtra("tipRate", .15);

        // Super View
        final LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

        //Color Dot
        colorDot = new TextView(this);
        colorDot.setText("o");
        colorDot.setTextColor(colors.get(number % colors.size()));

        // Checkboxes
        boxes = new ArrayList<CheckBox>();

        Intent getRContents = getIntent();
        items = (RContents) getRContents.getSerializableExtra("receipt");

        ArrayList<REntry> list = items.items;
        for (int i = 0; i < list.size(); i++){
            CheckBox check = new CheckBox(getApplicationContext());
            check.setText(list.get(i).getName()+"   "+list.get(i).getPrice());
            ll.addView(check);
            boxes.add(check);
        }

        // Buttons
        Button done = new Button(this);
        done.setText("Done");
        ll.addView(done);

        Button next = new Button(this);
        next.setText("Next");
        ll.addView(done);

        //Person
        List<Person> users = new ArrayList<Person>();


        this.setContentView(sv);

    }

    public void nextButtonClicked(View view) {
        ArrayList<REntry> list = items.items;
        Person user = new Person(colors.get(number), tr, tipRate);
        for (int i = 0; i < boxes.size(); i++){
            CheckBox check = boxes.get(i);
            if (check.isChecked()) {
                user.addREntry(list.get(i));
            }
        }
        users.add(user);
        for (int i = 0; i < boxes.size(); i++){
            CheckBox check = boxes.get(i);
            if (check.isChecked()){
                check.toggle();
            }
        }
        number++;
        colorDot.setTextColor(number);
        Intent intent = getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
    }

    public void doneButtonClicked(View view) {
        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("users", (Serializable) users);
    }
}
