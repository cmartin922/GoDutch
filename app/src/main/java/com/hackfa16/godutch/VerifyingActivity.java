package com.hackfa16.godutch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class VerifyingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifying);
        ListView lv = (ListView) findViewById(R.id.gridView1);

        Intent intent = getIntent();
        RContents data = (RContents)intent.getSerializableExtra("receipt");
        List<REntry> items = data.getItems();
        List<String> lines = new ArrayList<>();
        for(REntry i:items){
            lines.add(i.getName()+" "+i.getPrice());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, lines);

        lv.setAdapter(arrayAdapter);
    }

    public void satisfied(View view){
        // navigate to selector
        Intent i = new Intent(this,TipActivity.class);
        startActivity(i);
    }

    public void notSatisfied(View view){
        // navigate to selector
        Intent i = new Intent(this,LandingActivity.class);
        startActivity(i);
    }
}