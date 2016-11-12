package com.hackfa16.godutch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {

    public ArrayList<String> passings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();
        passings = (ArrayList<String>) intent.getSerializableExtra("passings");

        ListView lv = (ListView) findViewById(R.id.summary);


        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_summary, passings);


//        for (int i = 0; i < passings.size(); i++) {
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_summary, summaries.get(i));
//            lv.setAdapter(arrayAdapter);
//        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
