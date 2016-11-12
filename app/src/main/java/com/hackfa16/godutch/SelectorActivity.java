package com.hackfa16.godutch;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectorActivity extends AppCompatActivity implements View.OnClickListener {

    public RContents items;
    public List<Person> users;
    public List<CheckBox> boxes;
    public List<Integer> colors;
    public int number = 0;
    public double tr;
    public double tipRate;
    TextView colorDot;
    public List<String> passings;
    Button done;
    Button next;
    public static final int DONE_BUTTON_ID = 1;
    public static final int NEXT_BUTTON_ID = 2;

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

        passings = new ArrayList<String>();

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        items = (RContents) (extra.getSerializable("receipt"));
        System.out.println("selector");
        System.out.println(items==null);

        // Tax Rate
        tr = (items.getTax().getPrice())/(items.getTotal().getPrice());

        // Tip

        tipRate = extra.getDouble("tipRate", .15);

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
            check.setTextColor(Color.BLACK);
            ll.addView(check);
            boxes.add(check);
        }

        // Buttons
        done = new Button(this);
        done.setText("Done");
        done.setOnClickListener(this);
        ll.addView(done);

        next = new Button(this);
        next.setText("Next");
        next.setOnClickListener(this);
        ll.addView(done);

        //Person
        List<Person> users = new ArrayList<Person>();


        this.setContentView(sv);

    }

//    public void nextButtonClicked(View view) {
//        ArrayList<REntry> list = items.items;
//        Person user = new Person(colors.get(number), tr, tipRate);
//
//        for (int i = 0; i < boxes.size(); i++){
//            CheckBox check = boxes.get(i);
//            if (check.isChecked()) {
//                user.addREntry(list.get(i));
//            }
//        }
//        users.add(user);
//        double total = user.getTotal();
//
//        String passing = total+"*"+number;
//        passings.add(passing);
//
//        for (int i = 0; i < boxes.size(); i++){
//            CheckBox check = boxes.get(i);
//            if (check.isChecked()){
//                check.toggle();
//            }
//        }
//        number++;
//        colorDot.setTextColor(number);
//        Intent intent = getIntent();
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        finish();
//        startActivity(intent);
//    }
//
//    public void doneButtonClicked(View view) {
//        Intent intent = new Intent(this, SummaryActivity.class);
//        intent.putExtra("users", (Serializable) users);
//        intent.putExtra("passings", (Serializable) passings);
//        startActivity(intent);
//    }

    @Override
    public void onClick(View view) {
        if (view.getId() == next.getId()) {
            System.out.println("next clicked");
            ArrayList<REntry> list = items.items;
            Person user = new Person(colors.get(number), tr, tipRate);

            for (int i = 0; i < boxes.size(); i++){
                CheckBox check = boxes.get(i);
                if (check.isChecked()) {
                    user.addREntry(list.get(i));
                }
            }
            users.add(user);
            double total = user.getTotal();

            String passing = total+"*"+number;
            passings.add(passing);

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
        if (view.getId() == done.getId()) {
            System.out.println("done clicked");
            Intent intent = new Intent(this, SummaryActivity.class);
            intent.putExtra("users", (Serializable) users);
            intent.putExtra("passings", (Serializable) passings);
            startActivity(intent);
        }
    }
}
