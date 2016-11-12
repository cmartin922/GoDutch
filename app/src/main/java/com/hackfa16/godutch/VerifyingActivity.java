package com.hackfa16.godutch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

public class VerifyingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifying);
        GridView gridview = (GridView) findViewById(R.id.gridView1) ;

    }

    public void satisfied(View view){
        // navigate to selector
        Intent i = new Intent(this,Selector.class);
        startActivity(i);
    }

    public void notSatisfied(View view){
        // navigate to selector
        Intent i = new Intent(this,LandingActivity.class);
        startActivity(i);
    }
}