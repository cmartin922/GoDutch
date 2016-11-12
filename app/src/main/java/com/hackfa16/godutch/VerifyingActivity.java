package com.hackfa16.godutch;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
    }

    public void notSatisfied(View view){

    }
}
