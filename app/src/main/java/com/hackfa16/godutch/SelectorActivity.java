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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SelectorActivity extends AppCompatActivity implements View.OnClickListener {

    public RContents items;
    public List<Person> users;
    public List<CheckBox> boxes;
    public List<Integer> nums;
    public List<Double> sums;
    public List<Integer> colors;
    public int number = 1;
    public double tr;
    public double tipRate;
    TextView colorDot;
    public List<String> passings;
    Button done;
    Button next;
    private static final int DONE_BUTTON_ID = 1;
    private static final int NEXT_BUTTON_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView sv = new ScrollView(this);

        // Colors
        colors = new ArrayList<Integer>();
        colors.add(Color.BLACK);
        colors.add(Color.BLUE);
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.GRAY);

        passings = new ArrayList<String>();

        Intent intent = getIntent();
        items = (RContents) intent.getSerializableExtra("receipts");
        System.out.println("selector");
        System.out.println(items==null);

        // Tax Rate
        tr = (items.getTax().getPrice())/(items.getTotal().getPrice());

        // Tip

        tipRate = intent.getDoubleExtra("tr", .15);

        // Super View
        final LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

        //Color Dot
        colorDot = new TextView(this);
        colorDot.setText("User 1");
        colorDot.setTextSize(15);
        colorDot.setTextColor(Color.BLACK);
        ll.addView(colorDot);

        // Checkboxes
        boxes = new ArrayList<CheckBox>();

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
        done.setId(DONE_BUTTON_ID);
        done.setOnClickListener(this);
        ll.addView(done);

        next = new Button(this);
        next.setText("Next");
        next.setId(NEXT_BUTTON_ID);
        next.setOnClickListener(this);
        ll.addView(next);

        //Person
        List<Person> users = new ArrayList<Person>();
        nums = new ArrayList<Integer>();
        sums = new ArrayList<Double>();

        this.setContentView(sv);

    }

    @Override
    public void onClick(View view) {
        System.out.println(view.getId());
        System.out.println(next.getId());
        System.out.println(done.getId());
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

            double total = user.getTotal();

            DecimalFormat df2 = new DecimalFormat("###.##");
            double roundedTotal = Double.valueOf(df2.format(total));
            nums.add(number);
            sums.add(roundedTotal);

            String passing = total+"*"+number;
            passings.add(passing);

            for (int i = 0; i < boxes.size(); i++){
                CheckBox check = boxes.get(i);
                if (check.isChecked()){
                    check.toggle();
                }
            }
            number++;
            colorDot.setText("User "+number);
        }
        if (view.getId() == done.getId()) {
            System.out.println("done clicked");
            System.out.println(sums.toString());
            Intent intent = new Intent(this, SummaryActivity.class);
//            intent.putExtra("users", (Serializable) users);
//            intent.putExtra("passings", (Serializable) passings);
            intent.putExtra("numbers", (Serializable) nums);
            intent.putExtra("totals", (Serializable) sums);
            startActivity(intent);
        }
    }
}
