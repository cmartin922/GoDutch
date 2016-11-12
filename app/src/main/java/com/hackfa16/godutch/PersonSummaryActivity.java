package com.hackfa16.godutch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PersonSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_summary);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView lv = (ListView) findViewById(R.id.gridView1);

        Intent intent = getIntent();
        Person person = (Person)intent.getSerializableExtra("person");

        List<REntry> items = person.getItems();

        ArrayAdapter<REntry> arrayAdapter = new ArrayAdapter<REntry>(
                this, android.R.layout.simple_list_item_1, items);

        lv.setAdapter(arrayAdapter);
    }

}
